package com.juliuscanute.multiconfig.ui.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.base.observeSingleEvent
import com.juliuscanute.multiconfig.databinding.ConfigurationFragmentBinding
import com.juliuscanute.multiconfig.ui.host.MainActivityViewModel
import com.juliuscanute.multiconfig.ui.adapter.ConfigurationAdapter


class ConfigurationFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val configurationViewModel: ConfigurationViewModel by viewModels()

    private lateinit var adapter: ConfigurationAdapter

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
        adapter = ConfigurationAdapter(configurationViewModel)
        binding.configurationList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurationViewModel.loadApplicationConfiguration()
        configurationViewModel.privateActions.observeSingleEvent(this) { state ->
            when (state) {
                is ConfigurationState.SelectedConfigurationState -> {
                    mainActivityViewModel.selectConfiguration(environment = state.environment)
                    val action =
                        ConfigurationFragmentDirections.actionConfigurationFragmentToConfigurationDetailFragment(
                            environment = state.environment
                        )
                    findNavController().navigate(action)
                }
                is ConfigurationState.LoadApplicationConfigurationState -> {
                    mainActivityViewModel.selectConfiguration(environment = state.environment)
                    adapter.submitList(state.items)
                }
            }
        }
    }
}
