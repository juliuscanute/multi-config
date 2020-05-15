package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.model.ConfigurationGetter
import com.juliuscanute.multiconfig.model.ConfigurationManager
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object MultiConfigure {
    private var starter: Starter? = null

    fun getConfigurationManager(): ConfigurationManager {
        checkNotNull(starter) { "starter must not be null" }
        return starter!!.getConfigurationManager()
    }

    fun getConfig(): ConfigurationGetter {
        checkNotNull(starter) { "starter must not be null" }
        return starter!!.getConfig()
    }

    fun getLaunchController(): ApplicationLaunchController {
        checkNotNull(starter) { "starter must not be null" }
        return starter!!.getLaunchController()
    }

    fun getEnvironment(): String {
        checkNotNull(starter) { "starter must not be null" }
        return starter!!.getEnvironment()
    }

    operator fun invoke(starter: Starter) {
        this.starter = starter
    }

    operator fun invoke(environment: String) {
        checkNotNull(starter) { "starter must not be null" }
        starter!!.setEnvironment(environment = environment)
    }
}