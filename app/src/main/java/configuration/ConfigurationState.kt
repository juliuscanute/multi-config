package configuration

import builder.ApplicationConfiguration
import model.Item

sealed class ConfigurationState {
    data class SelectedConfigurationState(val environment: String) : ConfigurationState()
    data class LoadApplicationConfigurationState(val items: ApplicationConfiguration) :
        ConfigurationState()

    data class LoadEnvironmentConfigurationState(val items: List<ItemState>) : ConfigurationState()
    data class ShowChoiceConfigurationState(
        val description: String,
        val items: ArrayList<Item>,
        val currentSelection: Int,
        val key: String
    ) :
        ConfigurationState()

    data class ShowEditableState(
        val description: String, val value: String, val key: String
    ) : ConfigurationState()
}