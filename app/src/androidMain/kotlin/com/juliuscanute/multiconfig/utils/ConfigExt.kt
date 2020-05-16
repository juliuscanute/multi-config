package com.juliuscanute.multiconfig.utils

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.juliuscanute.multiconfig.config.MultiConfigure
import com.juliuscanute.multiconfig.model.ConfigurationManager
import kotlin.reflect.KProperty

class ConfigManagerInitializer {
    operator fun getValue(service: ComponentActivity, property: KProperty<*>): ConfigurationManager {
        return MultiConfigure.getConfigurationManager()
    }

    operator fun getValue(service: ViewModel, property: KProperty<*>): ConfigurationManager {
        return MultiConfigure.getConfigurationManager()
    }
}

class IntentInitializer {
    operator fun getValue(service: ComponentActivity, property: KProperty<*>): Intent {
        val environment = MultiConfigure.getEnvironment()
        return MultiConfigure.getLaunchController().launchController(environment= environment) as Intent
    }
}