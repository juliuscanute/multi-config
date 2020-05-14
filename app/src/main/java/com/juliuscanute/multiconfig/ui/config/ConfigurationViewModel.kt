package com.juliuscanute.multiconfig.ui.config

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juliuscanute.multiconfig.builder.ApplicationConfiguration
import com.juliuscanute.multiconfig.base.SingleObserverEvent
import com.juliuscanute.multiconfig.ui.adapter.ConfigurationViewDataModel
import com.juliuscanute.multiconfig.utils.ConfigManagerInitializer
import com.juliuscanute.multiconfig.model.ConfigurationManager

class ConfigurationViewModel : ViewModel() {
    private val configManager: ConfigurationManager by ConfigManagerInitializer()
    private val privateState = MutableLiveData<SingleObserverEvent<ConfigurationState>>()

    val privateActions: LiveData<SingleObserverEvent<ConfigurationState>> = privateState

    fun moveToConfigurationDetail(environment: String) {
        privateState.postValue(
            SingleObserverEvent(
                ConfigurationState.SelectedConfigurationState(
                    environment = environment
                )
            )
        )
    }

    fun selectNewConfiguration(selected: Int): Boolean {
        configManager.saveConfig(selected)
        loadApplicationConfiguration()
        return true
    }

    fun loadApplicationConfiguration() {
        val applicationConfiguration = configManager.getApplicationConfiguration()
        val selectedConfig = configManager.getConfig()
        val selectedIndex = if (selectedConfig < 0) 0 else selectedConfig
        privateState.postValue(
            SingleObserverEvent(
                ConfigurationState.LoadApplicationConfigurationState(
                    applicationConfiguration.mapState(selectedIndex),
                    applicationConfiguration[selectedConfig].environment
                )
            )
        )
    }
}

fun ApplicationConfiguration.mapState(selectedIndex: Int = 0): List<ConfigurationViewDataModel> {
    return this.mapIndexed { index, configuration ->
        if (selectedIndex == index)
            ConfigurationViewDataModel(
                index = index,
                environment = configuration.environment,
                selected = true
            )
        else
            ConfigurationViewDataModel(
                index = index,
                environment = configuration.environment,
                selected = false
            )
    }
}