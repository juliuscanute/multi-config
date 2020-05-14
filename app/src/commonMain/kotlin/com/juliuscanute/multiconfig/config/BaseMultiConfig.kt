package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.builder.ApplicationConfiguration
import com.juliuscanute.multiconfig.model.ConfigurationGetter
import com.juliuscanute.multiconfig.model.ConfigurationManager
import com.juliuscanute.multiconfig.settings.Settings

class BaseMultiConfig<T>(private val settings: Settings? = null) : Starter {
    private var startController: ApplicationLaunchController? = null
    private var configManager: ConfigurationManager? = null
    private var environment: String = ""

    fun multiConfig(configuration: ApplicationConfiguration, controller: ApplicationLaunchController?) {
        checkNotNull(settings) { "Settings must not be empty" }
        val configManager = ConfigurationManager(settings = settings, repository = configuration)
        this.configManager = configManager
        if (this.environment.isEmpty()) {
            val applicationConfiguration = configManager.getApplicationConfiguration()
            val selectedConfig = configManager.getConfig()
            val selectedIndex = if (selectedConfig < 0) 0 else selectedConfig
            this.environment = applicationConfiguration[selectedIndex].environment
        }
        startController = controller
    }

    fun multiConfig(configuration: ApplicationConfiguration) {
        val configManager = ConfigurationManager(repository = configuration)
        this.configManager = configManager
        if (this.environment.isEmpty()) {
            val applicationConfiguration = configManager.getApplicationConfiguration()
            this.environment = applicationConfiguration.first().environment
        }
    }

    override fun getConfigurationManager(): ConfigurationManager {
        checkNotNull(configManager) { "configuration manager must not be empty" }
        return configManager!!
    }

    override fun getLaunchController(): ApplicationLaunchController {
        check(environment.isBlank()) { "environment must not be empty" }
        checkNotNull(startController) {"start controller is empty"}
        return startController!!
    }

    override fun getConfig(): ConfigurationGetter {
        checkNotNull(environment.isNotBlank()) { "environment must not be empty" }
        checkNotNull(configManager) { "configuration manager must not be empty" }
        return when (configManager!!.isSettingsInitialized()) {
            true -> configManager!!.getConfiguration(
                environment
            )
            else -> configManager!!.getImmutableConfiguration(
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