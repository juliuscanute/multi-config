package com.juliuscanute.multiconfig.ui.host

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.juliuscanute.multiconfig.MultiConfig
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.base.observeMultiEvent
import com.juliuscanute.multiconfig.databinding.ActivityMainBinding
import com.juliuscanute.multiconfig.ui.config.ConfigurationFragment
import com.juliuscanute.multiconfig.ui.configdetail.ConfigurationDetailFragment
import com.juliuscanute.multiconfig.utils.IntentInitializer
import com.juliuscanute.multiconfig.utils.buildViewModel

class MainActivity : AppCompatActivity(), ConfigurationFragment.Callbacks {

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        buildViewModel { MainActivityViewModel() }
    }
    private val startIntent: Intent by IntentInitializer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.inflate<ActivityMainBinding>(layoutInflater, R.layout.activity_main, null, false)
        binding.model = mainActivityViewModel
        mainActivityViewModel.commonActions.observeMultiEvent(this) { state ->
            when (state) {
                is CommonState.ButtonConfigurationState -> {
                    binding.materialButton.text = getString(R.string.launch_with, state.environment)
                    binding.toolbar.title = getString(R.string.app_name_with, state.environment)
                }
                is CommonState.ButtonTapState -> {
                    MultiConfig.environment = state.environment
                    try {
                        startActivity(startIntent)
                        finish()
                    } catch (e: Exception) {
                        AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("Start activity intent not set in Multiconfig.")
                            .setPositiveButton(android.R.string.ok, null).show()
                    }
                }
            }
        }

        setContentView(binding.root)
    }

    override fun onEnvironmentSelected(environment: String) {
        val fragment = ConfigurationDetailFragment.newInstance(environment = environment)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
