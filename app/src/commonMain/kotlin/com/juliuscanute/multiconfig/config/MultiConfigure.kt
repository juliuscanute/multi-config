package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.model.ConfigurationGetter
import com.juliuscanute.multiconfig.model.ConfigurationManager
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object MultiConfigure {
    private lateinit var configBase: ConfigBase

    fun getConfigurationManager(): ConfigurationManager {
        return configBase.getConfigurationManager()
    }

    fun getConfig(): ConfigurationGetter {
        return configBase.getConfig()
    }

    fun getLaunchController(): LaunchController<*> {
        return configBase.getLaunchController()
    }

    fun getEnvironment(): String {
        return configBase.getEnvironment()
    }

    operator fun invoke(configBase: ConfigBase) {
        this.configBase = configBase
    }

    operator fun invoke(environment: String) {
        configBase.setEnvironment(environment = environment)
    }
}