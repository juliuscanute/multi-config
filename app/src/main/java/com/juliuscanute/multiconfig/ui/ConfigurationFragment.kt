package com.juliuscanute.multiconfig.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.base.observeEvent
import com.juliuscanute.multiconfig.databinding.ConfigurationFragmentBinding
import com.juliuscanute.multiconfig.ui.adapter.ConfigurationAdapter
import com.juliuscanute.multiconfig.ui.state.ConfigurationState


class ConfigurationFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

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
        adapter = ConfigurationAdapter(mainActivityViewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivityViewModel.loadApplicationConfiguration()
        mainActivityViewModel.actions.observeEvent(this) { state ->
            when (state) {
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
            }
        }
    }
}
