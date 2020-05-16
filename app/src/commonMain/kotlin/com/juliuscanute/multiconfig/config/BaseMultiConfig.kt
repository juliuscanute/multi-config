package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.builder.ApplicationConfiguration
import com.juliuscanute.multiconfig.model.ConfigurationGetter
import com.juliuscanute.multiconfig.model.ConfigurationManager
import com.juliuscanute.multiconfig.settings.Settings

class BaseMultiConfig<T>(
    private val settings: Settings? = null,
    private val startController: LaunchController<*>? = null
) : ConfigBase {
    private lateinit var configManager: ConfigurationManager
    private var environment: String = ""

    fun multiConfig(configuration: ApplicationConfiguration) {
        var hasSettings = false
        configManager = if (settings != null && startController != null) {
            hasSettings = true
            ConfigurationManager(settings = settings, repository = configuration)
        } else {
            ConfigurationManager(repository = configuration)
        }
        if (hasSettings && environment.isEmpty()) {
            val applicationConfiguration = configManager.getApplicationConfiguration()
            val selectedConfig = configManager.getConfig()
            val selectedIndex = if (selectedConfig < 0) 0 else selectedConfig
            environment = applicationConfiguration[selectedIndex].environment
        } else if (environment.isEmpty()) {
            val applicationConfiguration = configManager.getApplicationConfiguration()
            environment = applicationConfiguration.first().environment
        }
    }

    override fun getConfigurationManager(): ConfigurationManager {
        return configManager
    }

    override fun getLaunchController(): LaunchController<*> {
        check(environment.isBlank()) { "environment must not be empty" }
        checkNotNull(startController) { "start controller is empty" }
        return startController
    }

    override fun getConfig(): ConfigurationGetter {
        checkNotNull(environment.isNotBlank()) { "environment must not be empty" }
        return when (configManager.isSettingsInitialized()) {
            true -> configManager.getConfiguration(
                environment
            )
            else -> configManager.getImmutableConfiguration(
                environment
            )
        }
    }

    override fun setEnvironment(environment: String) {
        this.environment = environment
    }

    override fun getEnvironment(): String {
        checkNotNull(environment.isNotBlank()) { "environment must not be empty" }
        return environment
    }

    operator fun invoke(body: BaseMultiConfig<T>.() -> Unit) {
        this.body()
    }
}