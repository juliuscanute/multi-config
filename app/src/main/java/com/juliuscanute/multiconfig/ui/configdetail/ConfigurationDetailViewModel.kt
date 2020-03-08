package com.juliuscanute.multiconfig.ui.configdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import builder.EnvironmentConfigurationImmutable
import com.juliuscanute.multiconfig.base.SingleObserverEvent
import com.juliuscanute.multiconfig.ui.adapter.ItemState
import model.ConfigurationManager
import model.ConfigurationRepository
import model.Item
import model.UiControlsModel

class ConfigurationDetailViewModel(private val configManager: ConfigurationManager) : ViewModel() {
    private val privateState = MutableLiveData<SingleObserverEvent<ConfigurationDetailState>>()
    private lateinit var manager: ConfigurationRepository

    val privateActions: LiveData<SingleObserverEvent<ConfigurationDetailState>> = privateState

    fun loadEnvironmentConfiguration(environment: String) {
        manager = configManager.getConfiguration(environment)
        loadUpdatedState()
    }

    fun showChoiceDialog(description: String, items: ArrayList<Item>, currentSelection: Int, key: String) {
        privateState.postValue(
            SingleObserverEvent(
                ConfigurationDetailState.ShowChoiceConfigurationState(
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
                ConfigurationDetailState.ShowEditableState(
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
                ConfigurationDetailState.LoadEnvironmentConfigurationState(
                    updatedConfig.mapState()
                )
            )
        )
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