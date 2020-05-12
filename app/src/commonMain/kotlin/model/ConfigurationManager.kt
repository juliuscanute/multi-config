package model

import builder.ApplicationConfiguration
import settings.Settings

class ConfigurationManager {
    private val repository: ApplicationConfiguration
    private val settings: Settings?

    companion object {
        const val KEY = "ConfigurationManager::Environment"
    }

    constructor(repository: ApplicationConfiguration, settings: Settings) {
        this.repository = repository
        this.settings = settings
    }

    constructor(repository: ApplicationConfiguration) {
        this.repository = repository
        this.settings = null
    }

    fun getApplicationConfiguration() = repository

    fun getConfiguration(environment: String): ConfigurationRepository {
        val configuration = repository.firstOrNull { it.environment == environment }
        checkNotNull(configuration) { "configuration mush not be null" }
        checkNotNull(settings) { "settings must not be empty" }
        return ConfigurationRepository(configuration.configs, settings, environment)
    }

    fun getImmutableConfiguration(environment: String): ImmutableConfigurationRepository {
        val configuration = repository.firstOrNull { it.environment == environment }
        checkNotNull(configuration) { "configuration mush not be null" }
        return ImmutableConfigurationRepository(configuration.configs, environment)
    }

    fun saveConfig(value: Int) {
        check(value < repository.size) { "Index must be within configuration range 0..${repository.size}" }
        checkNotNull(settings) { "settings must not be empty" }
        settings.putInt(KEY, value)
    }

    fun getConfig(): Int {
        checkNotNull(settings) { "settings must not be empty" }
        return settings.getInt(KEY, defaultValue = 0)
    }
}