package com.juliuscanute.multiconfig.utils

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import com.juliuscanute.multiconfig.MultiConfig
import com.juliuscanute.multiconfig.di.DependencyHandler
import model.ConfigurationManager
import kotlin.reflect.KProperty


class ConfigManagerInitializer {

    operator fun getValue(service: ComponentActivity, property: KProperty<*>): ConfigurationManager {
        return DependencyHandler.getConfigurationManager()
    }

    operator fun getValue(service: ViewModel, property: KProperty<*>): ConfigurationManager {
        return DependencyHandler.getConfigurationManager()
    }

    operator fun getValue(service: MultiConfig, property: KProperty<*>): ConfigurationManager {
        return DependencyHandler.getConfigurationManager()
    }

}


class IntentInitializer {
    operator fun getValue(service: ComponentActivity, property: KProperty<*>): Intent {
        return DependencyHandler.getIntent()
    }
}