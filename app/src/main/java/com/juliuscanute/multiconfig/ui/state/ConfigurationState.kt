package com.juliuscanute.multiconfig.ui.state

import com.juliuscanute.multiconfig.ui.adapter.ConfigurationViewDataModel
import com.juliuscanute.multiconfig.ui.adapter.ItemState
import model.Item

sealed class ConfigurationState {
    data class SelectedConfigurationState(val environment: String) : ConfigurationState()
    data class LoadApplicationConfigurationState(val items: List<ConfigurationViewDataModel>) :
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