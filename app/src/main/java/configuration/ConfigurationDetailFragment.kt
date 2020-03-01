package configuration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import configuration.databinding.ConfigurationDetailFragmentBinding
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
            }
        }
    }
}
