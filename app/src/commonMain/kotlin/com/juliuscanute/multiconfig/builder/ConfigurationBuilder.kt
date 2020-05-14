package com.juliuscanute.multiconfig.builder

import com.juliuscanute.multiconfig.model.UiControlsModel

class ConfigurationBuilder(private val environment: String) : Builder<Configuration> {

    private val configuration: MutableList<UiControlsModel> = mutableListOf()

    fun switch(switchBuilder: SwitchBuilder.() -> Unit) {
        configuration.add(SwitchBuilder().apply(switchBuilder).build())
    }

    fun range(rangeBuilder: RangeBuilder.() -> Unit) {
        configuration.add(RangeBuilder().apply(rangeBuilder).build())
    }

    fun editable(editableBuilder: EditableBuilder.() -> Unit) {
        configuration.add(EditableBuilder().apply(editableBuilder).build())
    }

    fun choice(choiceBuilder: ChoiceBuilder.() -> Unit) {
        configuration.add(ChoiceBuilder().apply(choiceBuilder).build())
    }

    override fun build(): Configuration =
        Configuration(configs = ArrayList(configuration), environment = environment)
}

data class Configuration(val configs: EnvironmentConfiguration, val environment: String)

typealias ApplicationConfiguration = ArrayList<Configuration>

typealias EnvironmentConfiguration = ArrayList<UiControlsModel>

typealias EnvironmentConfigurationImmutable = List<UiControlsModel>