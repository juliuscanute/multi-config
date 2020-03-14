package com.juliuscanute.multiconfig.ui.configdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.base.observeSingleEvent
import com.juliuscanute.multiconfig.databinding.ConfigurationDetailFragmentBinding
import com.juliuscanute.multiconfig.ui.ItemDivider
import com.juliuscanute.multiconfig.ui.adapter.ConfigurationDetailAdapter
import com.juliuscanute.multiconfig.utils.buildViewModel
import model.Item

private const val ARG_ENV_ID = "environment_id"

class ConfigurationDetailFragment : Fragment() {

    companion object {
        fun newInstance(environment: String): ConfigurationDetailFragment {
            val args = Bundle().apply {
                putString(ARG_ENV_ID, environment)
            }
            return ConfigurationDetailFragment().apply {
                arguments = args
            }
        }
    }

    private val configurationDetailViewModel: ConfigurationDetailViewModel by lazy {
        buildViewModel { ConfigurationDetailViewModel() }
    }

    lateinit var adapter: ConfigurationDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val environment: String = arguments?.getString(ARG_ENV_ID)
            ?: throw IllegalAccessException("arguments must not be empty")
        configurationDetailViewModel.loadEnvironmentConfiguration(environment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ConfigurationDetailFragmentBinding>(
            inflater,
            R.layout.configuration_detail_fragment,
            container,
            false
        )
        binding.configurationDetailList.layoutManager = LinearLayoutManager(requireContext())
        adapter = ConfigurationDetailAdapter(configurationDetailViewModel)
        binding.configurationDetailList.adapter = adapter
        binding.configurationDetailList.addItemDecoration(
            ItemDivider(
                requireContext(),
                R.drawable.item_divider
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurationDetailViewModel.privateActions.observeSingleEvent(this) { state ->
            when (state) {
                is ConfigurationDetailState.LoadEnvironmentConfigurationState -> {
                    adapter.submitList(state.items)
                }
                is ConfigurationDetailState.ShowChoiceConfigurationState -> {
                    showChoiceItems(
                        description = state.description,
                        items = state.items,
                        currentSelection = state.currentSelection,
                        key = state.key
                    )
                }
                is ConfigurationDetailState.ShowEditableState -> {
                    showEditable(description = state.description, value = state.value, key = state.key)
                }
            }
        }
    }

    private fun showChoiceItems(description: String, items: ArrayList<Item>, currentSelection: Int, key: String) {
        AlertDialog.Builder(requireContext()).setSingleChoiceItems(
            items.map { it.description }.toTypedArray(),
            currentSelection
        ) { dialog, which ->
            configurationDetailViewModel.savePairConfiguration(
                key = key,
                currentValue = items[which].description to which
            )
            dialog.dismiss()
        }.setTitle(description).setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }.show()
    }

    private fun showEditable(description: String, value: String, key: String) {
        val view = layoutInflater.inflate(R.layout.input_alert_dialog, null)
        val textInput = view.findViewById<AppCompatEditText>(R.id.editable_input)
        textInput.hint = description
        textInput.setText(value)
        textInput.setSelection(value.length)
        AlertDialog.Builder(requireContext())
            .setTitle(description)
            .setView(view)
            .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                configurationDetailViewModel.saveStringConfiguration(key, textInput.text.toString())
                dialog.dismiss()
            }.show()
    }
}
