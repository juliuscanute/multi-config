package configuration

import builder.ApplicationConfiguration

sealed class ConfigurationState {
    data class SelectedConfigurationState(val environment: String) : ConfigurationState()
    data class LoadApplicationConfigurationState(val items: ApplicationConfiguration) :
        ConfigurationState()

    data class LoadEnvironmentConfigurationState(val items: List<ItemState>) : ConfigurationState()
}