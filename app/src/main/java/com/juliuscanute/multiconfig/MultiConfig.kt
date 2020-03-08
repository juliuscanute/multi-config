package com.juliuscanute.multiconfig

import com.juliuscanute.multiconfig.utils.ConfigManagerInitializer
import model.ConfigurationManager
import model.ConfigurationRepository

object MultiConfig {
    private val configManager: ConfigurationManager by ConfigManagerInitializer()
    var environment: String = ""
    fun getConfig(): ConfigurationRepository {
        assert(environment.isNotBlank()) { "environment must not be empty" }
        return configManager.getConfiguration(environment)
    }
}