package com.juliuscanute.multiconfig.di

import android.content.Context
import android.content.Intent
import builder.ApplicationConfiguration
import com.russhwolf.settings.Settings
import model.ConfigurationManager
import settings.UserSettings

object DependencyHandler {

    private lateinit var settings: Settings
    private lateinit var configManager: ConfigurationManager
    private lateinit var intent: Intent


    fun appConfig(configuration: ApplicationConfiguration, intent: Intent) {
        configManager = ConfigurationManager(settings = settings, repository = configuration)
        this.intent = intent
    }

    internal fun getConfigurationManager() = configManager

    internal fun getIntent() = intent

    operator fun invoke(context: Context, body: DependencyHandler.() -> Unit) {
        settings = UserSettings().userSettings(context)
        this.body()
    }
}