package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.model.ConfigurationGetter
import com.juliuscanute.multiconfig.model.ConfigurationManager

interface ConfigBase {
    fun getConfigurationManager(): ConfigurationManager
    fun getConfig(): ConfigurationGetter
    fun setEnvironment(environment: String)
    fun getEnvironment(): String
    fun getLaunchController(): LaunchController<*>
}