package com.juliuscanute.multiconfig.ui.configdetail

import com.juliuscanute.multiconfig.ui.adapter.ItemState
import com.juliuscanute.multiconfig.model.Item

sealed class ConfigurationDetailState {
    data class LoadEnvironmentConfigurationState(val items: List<ItemState>) : ConfigurationDetailState()
    data class ShowChoiceConfigurationState(
        val description: String,
        val items: ArrayList<Item>,
        val currentSelection: Int,
        val key: String
    ) :
        ConfigurationDetailState()

    data class ShowEditableState(
        val description: String, val value: String, val key: String
    ) : ConfigurationDetailState()
}