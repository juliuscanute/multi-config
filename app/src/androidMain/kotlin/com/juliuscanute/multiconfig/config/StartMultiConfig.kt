package com.juliuscanute.multiconfig.config

import android.content.Context
import android.content.Intent
import com.juliuscanute.multiconfig.builder.ApplicationConfiguration
import com.juliuscanute.multiconfig.settings.Settings
import com.juliuscanute.multiconfig.settings.UserSettings
import com.juliuscanute.multiconfig.model.ConfigurationManager

object StartMultiConfig {

    private var settings: Settings? = null
    private lateinit var configManager: ConfigurationManager
    private var intent: Intent? = null


    fun multiConfig(configuration: ApplicationConfiguration, intent: Intent) {
        checkNotNull(settings) { "Settings must not be empty" }
        configManager = ConfigurationManager(settings = settings!!, repository = configuration)
        MultiConfig.configManager = configManager
        if (MultiConfig.environment.isEmpty()) {
            val applicationConfiguration = configManager.getApplicationConfiguration()
            val selectedConfig = configManager.getConfig()
            val selectedIndex = if (selectedConfig < 0) 0 else selectedConfig
            MultiConfig.environment = applicationConfiguration[selectedIndex].environment
        }
        StartMultiConfig.intent = intent
    }

    fun multiConfig(configuration: ApplicationConfiguration) {
        configManager = ConfigurationManager(repository = configuration)
        MultiConfig.configManager = configManager
        if (MultiConfig.environment.isEmpty()) {
            val applicationConfiguration = configManager.getApplicationConfiguration()
            MultiConfig.environment = applicationConfiguration.first().environment
        }
    }

    fun getConfigurationManager() =
        configManager

    fun getIntent() = intent!!

    operator fun invoke(context: Context, body: StartMultiConfig.() -> Unit) {
        settings = UserSettings().userSettings(context)
        checkNotNull(settings) { "Unable to initialize settings" }
        this.body()
    }

    operator fun invoke(body: StartMultiConfig.() -> Unit) {
        this.body()
    }
}

fun startMultiConfig(context: Context, body: StartMultiConfig.() -> Unit) {
    StartMultiConfig(context, body)
}

fun startMultiConfig(body: StartMultiConfig.() -> Unit) {
    StartMultiConfig(body)
}