package com.juliuscanute.multiconfig.di

import android.content.Context
import android.content.Intent
import builder.ApplicationConfiguration
import com.juliuscanute.multiconfig.MultiConfig
import model.ConfigurationManager
import settings.Settings
import settings.UserSettings

object StartMultiConfig {

    private lateinit var settings: Settings
    private lateinit var configManager: ConfigurationManager
    private lateinit var intent: Intent


    fun appConfig(configuration: ApplicationConfiguration, intent: Intent) {
        configManager = ConfigurationManager(settings = settings, repository = configuration)
        if (MultiConfig.environment.isEmpty()) {
            val applicationConfiguration = configManager.getApplicationConfiguration()
            val selectedConfig = configManager.getConfig()
            val selectedIndex = if (selectedConfig < 0) 0 else selectedConfig
            MultiConfig.environment = applicationConfiguration[selectedIndex].environment
        }
        this.intent = intent
    }

    fun getConfigurationManager() = configManager

    fun getIntent() = intent

    operator fun invoke(context: Context, body: StartMultiConfig.() -> Unit) {
        settings = UserSettings().userSettings(context)
        this.body()
    }
}

fun startMultiConfig(context: Context, body: StartMultiConfig.() -> Unit) {
    StartMultiConfig(context, body)
}