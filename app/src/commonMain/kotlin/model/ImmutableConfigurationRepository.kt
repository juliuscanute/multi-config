package model

import builder.EnvironmentConfiguration
import builder.EnvironmentConfigurationImmutable
import settings.Settings

class ImmutableConfigurationRepository(
    private val configs: EnvironmentConfiguration,
    private val environment: String
) : ConfigurationGetterImplementation(configs, null, environment) {
    fun getEnvironmentConfiguration(): EnvironmentConfigurationImmutable = loadConfiguration(configs = configs, environment = environment)

    override fun loadConfiguration(configs: EnvironmentConfiguration, settings: Settings?, environment: String): List<UiControlsModel> =
        configs.map {
            when (it) {
                is UiControlsModel.Switch -> {
                    val key = environment + PREFIX + it.key
                    val newValue: Boolean = it.switchValue
                    store[key] = StoreValue.BooleanValue(value = newValue)
                    it.copy(switchValue = newValue)
                }
                is UiControlsModel.Range -> {
                    val key = environment + PREFIX + it.key
                    val newValue: Int = it.currentValue
                    store[key] = StoreValue.IntValue(value = it.currentValue)

                    it.copy(currentValue = newValue)
                }
                is UiControlsModel.Editable -> {
                    val key = environment + PREFIX + it.key

                    val newValue: String = it.currentValue
                    store[key] = StoreValue.StringValue(value = newValue)

                    it.copy(currentValue = newValue)
                }
                is UiControlsModel.Choice -> {
                    val key = environment + PREFIX + it.key
                    check(it.currentChoiceIndex < it.items.size) { "Choice mush be less than the available items" }
                    val item = it.items[it.currentChoiceIndex]
                    val newIntValue: Int = it.currentChoiceIndex
                    store[key] =
                        StoreValue.KeyValue(
                            key = item.information,
                            value = it.currentChoiceIndex
                        )
                    it.copy(currentChoiceIndex = newIntValue)
                }
            }
        }.toList()
}