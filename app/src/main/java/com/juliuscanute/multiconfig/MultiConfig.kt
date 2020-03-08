package com.juliuscanute.multiconfig

import android.content.Intent
import model.ConfigurationGetter
import kotlin.reflect.KProperty

object MultiConfig {

    var configuration: ConfigurationGetter by ConfigInitializer()
    var intent: Intent by IntentInitializer()

    fun getConfigInt(userKey: String) = configuration.getConfigInt(userKey)

    fun getConfigString(userKey: String) = configuration.getConfigString(userKey)


    fun getConfigBoolean(userKey: String) = configuration.getConfigBoolean(userKey)

    fun getConfigPair(userKey: String) = configuration.getConfigPair(userKey)
}

class ConfigInitializer {
    private lateinit var backingField: ConfigurationGetter

    operator fun getValue(service: MultiConfig, property: KProperty<*>): ConfigurationGetter {
        return backingField
    }

    operator fun setValue(service: MultiConfig, property: KProperty<*>, value: ConfigurationGetter) {
        backingField = value
    }
}

class IntentInitializer {
    private lateinit var backingField: Intent

    operator fun getValue(service: MultiConfig, property: KProperty<*>): Intent {
        return backingField
    }

    operator fun setValue(service: MultiConfig, property: KProperty<*>, value: Intent) {
        backingField = value
    }
}