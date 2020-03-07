package com.juliuscanute.multiconfig.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.base.observeSingleEvent
import com.juliuscanute.multiconfig.databinding.ConfigurationDetailFragmentBinding
import com.juliuscanute.multiconfig.ui.adapter.ConfigurationDetailAdapter
import com.juliuscanute.multiconfig.ui.state.ConfigurationState
import model.Item


class ConfigurationDetailFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    lateinit var adapter: ConfigurationDetailAdapter
    private val args: ConfigurationDetailFragmentArgs by navArgs()
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
        adapter = ConfigurationDetailAdapter(mainActivityViewModel)
        binding.configurationDetailList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivityViewModel.loadEnvironmentConfiguration(args.environment)
        mainActivityViewModel.privateActions.observeSingleEvent(this) { state ->
            when (state) {
                is ConfigurationState.LoadEnvironmentConfigurationState -> {
                    adapter.submitList(state.items)
                }
                is ConfigurationState.ShowChoiceConfigurationState -> {
                    showChoiceItems(
                        description = state.description,
                        items = state.items,
                        currentSelection = state.currentSelection,
                        key = state.key
                    )
                }
                is ConfigurationState.ShowEditableState -> {
                    showEditable(description = state.description, value = state.value, key = state.key)
                }
            }
        }
    }

    private fun showChoiceItems(description: String, items: ArrayList<Item>, currentSelection: Int, key: String) {
        MaterialAlertDialogBuilder(requireContext()).setSingleChoiceItems(
            items.map { it.description }.toTypedArray(),
            currentSelection
        ) { dialog, which ->
            mainActivityViewModel.savePairConfiguration(key = key, currentValue = items[which].description to which)
            dialog.dismiss()
        }.setTitle(description).setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }.show()
    }

    private fun showEditable(description: String, value: String, key: String) {
        val view = layoutInflater.inflate(R.layout.input_alert_dialog, null)
        val textInput = view.findViewById<TextInputEditText>(R.id.editable_input)
        textInput.hint = description
        textInput.setText(value)
        textInput.setSelection(value.length)
        AlertDialog.Builder(requireContext())
            .setTitle(description)
            .setView(view)
            .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                mainActivityViewModel.saveStringConfiguration(key, textInput.text.toString())
                dialog.dismiss()
            }.show()
    }
}
