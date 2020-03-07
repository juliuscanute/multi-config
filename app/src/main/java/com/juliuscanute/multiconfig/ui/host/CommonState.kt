package com.juliuscanute.multiconfig.ui.host

sealed class CommonState {
    data class ButtonConfigurationState(val environment: String) : CommonState()
}