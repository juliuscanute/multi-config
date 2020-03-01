package model

import builder.ApplicationConfiguration
import com.russhwolf.settings.Settings

class ConfigurationManager(
    private val repository: ApplicationConfiguration,
    private val settings: Settings
) {

    fun getApplicationConfiguration() = repository

    fun getConfiguration(environment: String): ConfigurationRepository {
        val configuration = repository.firstOrNull { it.environment == environment }
        checkNotNull(configuration) { "configuration mush not be null" }
        return ConfigurationRepository(configuration.configs, settings)
    }
}