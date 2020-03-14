package com.juliuscanute.multiconfig.ui.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.base.observeSingleEvent
import com.juliuscanute.multiconfig.ui.adapter.ConfigurationAdapter
import com.juliuscanute.multiconfig.ui.host.MainActivityViewModel
import com.juliuscanute.multiconfig.utils.buildViewModel
import kotlinx.android.synthetic.main.com_juliuscanute_multiconfig_configuration_fragment.*


class ConfigurationFragment : Fragment() {

    interface Callbacks {
        fun onEnvironmentSelected(environment: String)
    }

    private var callbacks: Callbacks? = null

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        buildViewModel(requireActivity()) { MainActivityViewModel() }
    }

    private val configurationViewModel: ConfigurationViewModel by lazy {
        buildViewModel { ConfigurationViewModel() }
    }

    private lateinit var adapter: ConfigurationAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.com_juliuscanute_multiconfig_configuration_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuration_list.layoutManager = LinearLayoutManager(requireContext())
        adapter = ConfigurationAdapter(configurationViewModel)
        configuration_list.adapter = adapter
        configurationViewModel.loadApplicationConfiguration()
        configurationViewModel.privateActions.observeSingleEvent(this) { state ->
            when (state) {
                is ConfigurationState.SelectedConfigurationState -> {
                    mainActivityViewModel.selectConfiguration(environment = state.environment)
                    callbacks?.onEnvironmentSelected(environment = state.environment)
                }
                is ConfigurationState.LoadApplicationConfigurationState -> {
                    mainActivityViewModel.selectConfiguration(environment = state.environment)
                    adapter.submitList(state.items)
                }
            }
        }
    }
}
