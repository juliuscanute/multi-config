package com.juliuscanute.multiconfig.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import builder.ApplicationConfiguration
import builder.EnvironmentConfigurationImmutable
import com.juliuscanute.multiconfig.base.MultiObserverEvent
import com.juliuscanute.multiconfig.base.SingleObserverEvent
import com.juliuscanute.multiconfig.ui.adapter.ConfigurationViewDataModel
import com.juliuscanute.multiconfig.ui.adapter.ItemState
import com.juliuscanute.multiconfig.ui.state.ConfigurationState
import model.ConfigurationManager
import model.ConfigurationRepository
import model.Item
import model.UiControlsModel

class MainActivityViewModel(private val configManager: ConfigurationManager) : ViewModel() {
    private val privateState = MutableLiveData<SingleObserverEvent<ConfigurationState>>()
    private val commonState = MutableLiveData<MultiObserverEvent<ConfigurationState>>()
    private lateinit var manager: ConfigurationRepository

    val privateActions: LiveData<SingleObserverEvent<ConfigurationState>> = privateState
    val commonActions: LiveData<MultiObserverEvent<ConfigurationState>> = commonState

    fun moveToConfigurationDetail(environment: String) {
        privateState.postValue(
            SingleObserverEvent(
                ConfigurationState.SelectedConfigurationState(
                    environment = environment
                )
            )
        )
        commonState.postValue(
            MultiObserverEvent(
                ConfigurationState.ButtonConfigurationState(
                    environment
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
                    applicationConfiguration.mapState(selectedIndex)
                )
            )
        )
        commonState.postValue(
            MultiObserverEvent(
                ConfigurationState.ButtonConfigurationState(
                    applicationConfiguration[selectedIndex].environment
                )
            )
        )
    }

    fun loadEnvironmentConfiguration(environment: String) {
        manager = configManager.getConfiguration(environment)
        loadUpdatedState()
    }

    fun showChoiceDialog(description: String, items: ArrayList<Item>, currentSelection: Int, key: String) {
        privateState.postValue(
            SingleObserverEvent(
                ConfigurationState.ShowChoiceConfigurationState(
                    description = description,
                    items = items,
                    currentSelection = currentSelection,
                    key = key
                )
            )
        )
    }

    fun showEditableDialog(description: String, value: String, key: String) {
        privateState.postValue(
            SingleObserverEvent(
                ConfigurationState.ShowEditableState(
                    description = description,
                    value = value,
                    key = key
                )
            )
        )
    }

    fun saveBooleanConfiguration(key: String, currentValue: Boolean) {
        manager.saveConfig(key, !currentValue)
        loadUpdatedState()
    }

    fun saveIntConfiguration(key: String, currentValue: Int) {
        manager.saveConfig(key, currentValue)
        loadUpdatedState()
    }

    fun saveStringConfiguration(key: String, currentValue: String) {
        manager.saveConfig(key, currentValue)
        loadUpdatedState()
    }

    fun savePairConfiguration(key: String, currentValue: Pair<String, Int>) {
        manager.saveConfig(key, currentValue)
        loadUpdatedState()
    }

    private fun loadUpdatedState() {
        val updatedConfig = manager.getEnvironmentConfiguration()
        privateState.postValue(
            SingleObserverEvent(
                ConfigurationState.LoadEnvironmentConfigurationState(
                    updatedConfig.mapState()
                )
            )
        )
    }
}

fun ApplicationConfiguration.mapState(selectedIndex: Int = 0): List<ConfigurationViewDataModel> {
    return this.mapIndexed { index, configuration ->
        if (selectedIndex == index)
            ConfigurationViewDataModel(index = index, environment = configuration.environment, selected = true)
        else
            ConfigurationViewDataModel(index = index, environment = configuration.environment, selected = false)
    }
}

fun EnvironmentConfigurationImmutable.mapState(): List<ItemState> {
    return this.map {
        when (it) {
            is UiControlsModel.Switch -> ItemState.SwitchState(
                key = it.key,
                description = it.description,
                switchValue = it.switchValue
            )
            is UiControlsModel.Range -> ItemState.RangeState(
                key = it.key,
                description = it.description,
                min = it.min,
                max = it.max,
                currentValue = it.currentValue
            )
            is UiControlsModel.Editable -> ItemState.EditableState(
                key = it.key,
                description = it.description,
                currentValue = it.currentValue
            )
            is UiControlsModel.Choice -> ItemState.ChoiceState(
                key = it.key,
                description = it.description,
                items = it.items,
                currentChoiceIndex = it.currentChoiceIndex
            )
        }
    }

}