package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.builder.ApplicationConfiguration
import com.juliuscanute.multiconfig.model.ConfigurationGetter
import com.juliuscanute.multiconfig.model.ConfigurationManager
import com.juliuscanute.multiconfig.settings.Settings
import com.juliuscanute.multiconfig.settings.UserSettings
import platform.UIKit.UIViewController

object StartMultiConfig {

    private var settings: Settings? = null
    private var startController: ApplicationLaunchController? = null


    fun multiConfig(configuration: ApplicationConfiguration, controller: ApplicationLaunchController?) {
        checkNotNull(settings) { "Settings must not be empty" }
        val configManager = ConfigurationManager(settings = settings!!, repository = configuration)
        MultiConfig.configManager = configManager
        if (MultiConfig.environment.isEmpty()) {
            val applicationConfiguration = configManager.getApplicationConfiguration()
            val selectedConfig = configManager.getConfig()
            val selectedIndex = if (selectedConfig < 0) 0 else selectedConfig
            MultiConfig.environment = applicationConfiguration[selectedIndex].environment
        }
        startController = controller
    }

    fun multiConfig(configuration: ApplicationConfiguration) {
        val configManager = ConfigurationManager(repository = configuration)
        MultiConfig.configManager = configManager
        if (MultiConfig.environment.isEmpty()) {
            val applicationConfiguration = configManager.getApplicationConfiguration()
            MultiConfig.environment = applicationConfiguration.first().environment
        }
    }

    fun getConfigurationManager() = MultiConfig.configManager

    fun getLaunchController() = startController!!.launchController(MultiConfig.environment)

    operator fun invoke(rootGroup: String, body: StartMultiConfig.() -> Unit) {
        settings = UserSettings().userSettings(rootGroup)
        checkNotNull(settings) { "Unable to initialize settings" }
        this.body()
    }

    operator fun invoke(body: StartMultiConfig.() -> Unit) {
        this.body()
    }
}

fun startMultiConfig(rootGroup: String, body: StartMultiConfig.() -> Unit) {
    StartMultiConfig(rootGroup, body)
}

fun startMultiConfig(body: StartMultiConfig.() -> Unit) {
    StartMultiConfig(body)
}

fun getConfig(): ConfigurationGetter = MultiConfig.getConfig()
//
//interface ConfigurationRootController {
//    fun getRootController(): UINavigationController
//}

interface ApplicationLaunchController {
    fun launchController(environment: String): UIViewController
}