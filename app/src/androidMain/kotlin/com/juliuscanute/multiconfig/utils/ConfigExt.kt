package com.juliuscanute.multiconfig.utils

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.juliuscanute.multiconfig.config.MultiConfig
import com.juliuscanute.multiconfig.model.ConfigurationManager
import kotlin.reflect.KProperty

class ConfigManagerInitializer {
    operator fun getValue(service: ComponentActivity, property: KProperty<*>): ConfigurationManager {
        return MultiConfig.getConfigurationManager()
    }

    operator fun getValue(service: ViewModel, property: KProperty<*>): ConfigurationManager {
        return MultiConfig.getConfigurationManager()
    }
}

class IntentInitializer {
    operator fun getValue(service: ComponentActivity, property: KProperty<*>): Intent {
        val environment = MultiConfig.getEnvironment()
        return MultiConfig.getLaunchController().launchController(environment= environment)
    }
}