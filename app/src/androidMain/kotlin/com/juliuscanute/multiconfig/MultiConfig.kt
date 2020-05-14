package com.juliuscanute.multiconfig

import com.juliuscanute.multiconfig.model.ConfigurationGetter
import com.juliuscanute.multiconfig.utils.ConfigManagerInitializer
import com.juliuscanute.multiconfig.model.ConfigurationManager

object MultiConfig {
    private val configManager: ConfigurationManager by ConfigManagerInitializer()
    var environment: String = ""
    fun getConfig(): ConfigurationGetter {
        assert(environment.isNotBlank()) { "environment must not be empty" }
        return when(configManager.isSettingsInitialized()) {
            true -> configManager.getConfiguration(
                environment
            )
            else -> configManager.getImmutableConfiguration(
                environment
            )
        }
    }
}