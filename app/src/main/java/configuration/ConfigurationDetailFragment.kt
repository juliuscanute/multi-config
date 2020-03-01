package configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import configuration.databinding.ConfigurationDetailFragmentBinding
import model.Item
import org.koin.android.viewmodel.ext.android.viewModel


class ConfigurationDetailFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()
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
        mainActivityViewModel.actions.observe(this) {
            when (val state = it.getContentIfNotHandled()) {
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
}
