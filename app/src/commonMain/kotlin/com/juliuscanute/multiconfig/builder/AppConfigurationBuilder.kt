package com.juliuscanute.multiconfig.builder

import com.juliuscanute.multiconfig.dsl.ConfigurationDslMarker

class AppConfigurationBuilder : Builder<ArrayList<Configuration>> {

    private val configuration: MutableList<Configuration> = mutableListOf()

    fun config(environment: String, configurationBuilder: ConfigurationBuilder.() -> Unit) {
        configuration.add(ConfigurationBuilder(environment).apply(configurationBuilder).build())
    }

    override fun build(): ArrayList<Configuration> = ArrayList(configuration)
}

@ConfigurationDslMarker
fun appConfig(
    config: AppConfigurationBuilder.() -> Unit
): ApplicationConfiguration = AppConfigurationBuilder().apply(config).build()