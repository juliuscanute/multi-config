package model

import builder.ApplicationConfiguration
import settings.Settings

class ConfigurationManager(
    private val repository: ApplicationConfiguration,
    private val settings: Settings
) {
    companion object {
        const val KEY = "ConfigurationManager::Environment"
    }

    fun getApplicationConfiguration() = repository

    fun getConfiguration(environment: String): ConfigurationRepository {
        val configuration = repository.firstOrNull { it.environment == environment }
        checkNotNull(configuration) { "configuration mush not be null" }
        return ConfigurationRepository(configuration.configs, settings)
    }

    fun saveConfig(value: Int) {
        check(value < repository.size) { "Index must be within configuration range 0..${repository.size}" }
        settings.putInt(KEY, value)
    }

    fun getConfig(): Int {
        return settings.getInt(KEY, defaultValue = -1)
    }
}