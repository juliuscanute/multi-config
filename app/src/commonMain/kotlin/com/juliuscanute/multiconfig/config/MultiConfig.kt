package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.model.ConfigurationGetter
import com.juliuscanute.multiconfig.model.ConfigurationManager
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object MultiConfig {
    var configManager: ConfigurationManager? = null
    var environment: String = ""
    fun getConfig(): ConfigurationGetter {
        checkNotNull(environment.isNotBlank()) { "environment must not be empty" }
        checkNotNull(configManager){"configuration manager must not be empty"}
        return when(configManager!!.isSettingsInitialized()) {
            true -> configManager!!.getConfiguration(
                environment
            )
            else -> configManager!!.getImmutableConfiguration(
                environment
            )
        }
    }
}