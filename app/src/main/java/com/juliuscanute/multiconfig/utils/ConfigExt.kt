package com.juliuscanute.multiconfig.utils

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.juliuscanute.multiconfig.MultiConfig
import com.juliuscanute.multiconfig.di.StartMultiConfig
import model.ConfigurationManager
import kotlin.reflect.KProperty


class ConfigManagerInitializer {

    operator fun getValue(service: ComponentActivity, property: KProperty<*>): ConfigurationManager {
        return StartMultiConfig.getConfigurationManager()
    }

    operator fun getValue(service: ViewModel, property: KProperty<*>): ConfigurationManager {
        return StartMultiConfig.getConfigurationManager()
    }

    operator fun getValue(service: MultiConfig, property: KProperty<*>): ConfigurationManager {
        return StartMultiConfig.getConfigurationManager()
    }

}


class IntentInitializer {
    operator fun getValue(service: ComponentActivity, property: KProperty<*>): Intent {
        return StartMultiConfig.getIntent()
    }
}