package com.juliuscanute.multiconfig.ui.config

import com.juliuscanute.multiconfig.ui.adapter.ConfigurationViewDataModel

sealed class ConfigurationState {
    data class SelectedConfigurationState(val environment: String) : ConfigurationState()
    data class LoadApplicationConfigurationState(val items: List<ConfigurationViewDataModel>, val environment: String) :
        ConfigurationState()
}