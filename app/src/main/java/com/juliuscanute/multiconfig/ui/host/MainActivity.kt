package com.juliuscanute.multiconfig.ui.host

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.base.observeMultiEvent
import com.juliuscanute.multiconfig.config.MultiConfig
import com.juliuscanute.multiconfig.ui.config.ConfigurationFragment
import com.juliuscanute.multiconfig.ui.configdetail.ConfigurationDetailFragment
import com.juliuscanute.multiconfig.utils.IntentInitializer
import com.juliuscanute.multiconfig.utils.buildViewModel
import kotlinx.android.synthetic.main.com_juliuscanute_multiconfig_activity_main.*

class MainActivity : AppCompatActivity(), ConfigurationFragment.Callbacks {

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        buildViewModel { MainActivityViewModel() }
    }
    private val startIntent: Intent by IntentInitializer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.com_juliuscanute_multiconfig_activity_main)
        launchButton.setOnClickListener {
            mainActivityViewModel.onTap()
        }
        mainActivityViewModel.commonActions.observeMultiEvent(this) { state ->
            when (state) {
                is CommonState.ButtonConfigurationState -> {
                    launchButton.text = getString(R.string.julius_multi_config_launch_with, state.environment)
                    toolbar.title = getString(R.string.julius_multi_config_app_name_with, state.environment)
                }
                is CommonState.ButtonTapState -> {
                    MultiConfig(environment = state.environment)
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
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = ConfigurationFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
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
