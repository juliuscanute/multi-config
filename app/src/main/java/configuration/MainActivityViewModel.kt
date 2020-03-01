package configuration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import builder.EnvironmentConfigurationImmutable
import model.ConfigurationManager
import model.ConfigurationRepository
import model.UiControlsModel

class MainActivityViewModel(private val configManager: ConfigurationManager) : ViewModel() {
    private val state = MutableLiveData<Event<ConfigurationState>>()
    private lateinit var manager: ConfigurationRepository
    val actions: LiveData<Event<ConfigurationState>> = state

    fun moveToConfigurationDetail(environment: String) {
        state.postValue(Event(ConfigurationState.SelectedConfigurationState(environment = environment)))
    }

    fun loadApplicationConfiguration() {
        state.postValue(Event(ConfigurationState.LoadApplicationConfigurationState(configManager.getApplicationConfiguration())))
    }

    fun loadEnvironmentConfiguration(environment: String) {
        manager = configManager.getConfiguration(environment)
        val updatedConfig = manager.getEnvironmentConfiguration()
        state.postValue(Event(ConfigurationState.LoadEnvironmentConfigurationState(updatedConfig.mapState())))
    }

    fun saveBooleanConfiguration(key: String, currentValue: Boolean) {
        manager.saveConfig(key, !currentValue)
    }

    fun saveIntConfiguration(key: String, currentValue: Int){
        manager.saveConfig(key, currentValue)
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
                step = it.step,
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