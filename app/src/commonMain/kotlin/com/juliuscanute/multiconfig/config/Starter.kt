package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.model.ConfigurationGetter
import com.juliuscanute.multiconfig.model.ConfigurationManager

interface Starter {
    fun getConfigurationManager(): ConfigurationManager
    fun getConfig(): ConfigurationGetter
    fun setEnvironment(environment: String)
    fun getEnvironment(): String
    fun getLaunchController(): ApplicationLaunchController
}