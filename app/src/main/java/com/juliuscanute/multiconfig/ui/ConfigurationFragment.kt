package com.juliuscanute.multiconfig.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.databinding.ConfigurationFragmentBinding
import com.juliuscanute.multiconfig.ui.adapter.ConfigurationAdapter
import com.juliuscanute.multiconfig.ui.state.ConfigurationState
import org.koin.android.viewmodel.ext.android.viewModel


class ConfigurationFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

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
