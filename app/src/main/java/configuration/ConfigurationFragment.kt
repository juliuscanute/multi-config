package configuration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import configuration.databinding.ConfigurationFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel


class ConfigurationFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    lateinit var adapter: ConfigurationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ConfigurationFragmentBinding>(
            inflater,
            R.layout.configuration_fragment,
            container,
            false
        )
        binding.configurationList.layoutManager = LinearLayoutManager(requireContext())
        adapter = ConfigurationAdapter(mainActivityViewModel)
        binding.configurationList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivityViewModel.loadApplicationConfiguration()
        mainActivityViewModel.actions.observe(this) {
            when (val state = it.getContentIfNotHandled()) {
                is ConfigurationState.SelectedConfigurationState -> {
                    val action =
                        ConfigurationFragmentDirections.actionConfigurationFragmentToConfigurationDetailFragment(
                            environment = state.environment
                        )
                    findNavController().navigate(action)
                }
                is ConfigurationState.LoadApplicationConfigurationState -> {
                    adapter.submitList(state.items)
                }
                else -> {
                }
            }
        }
    }
}
