package model

import builder.EnvironmentConfiguration
import builder.EnvironmentConfigurationImmutable

class ImmutableConfigurationRepository(
    private val configs: EnvironmentConfiguration
) : ConfigurationGetterImplementation() {
    fun getEnvironmentConfiguration(): EnvironmentConfigurationImmutable = loadConfiguration()

    override fun loadConfiguration(): List<UiControlsModel> = configs.map {
        when (it) {
            is UiControlsModel.Switch -> {
                val key = PREFIX + it.key
                val newValue: Boolean = it.switchValue
                store[key] = StoreValue.BooleanValue(value = newValue)
                it.copy(switchValue = newValue)
            }
            is UiControlsModel.Range -> {
                val key = PREFIX + it.key
                val newValue: Int = it.currentValue
                store[key] = StoreValue.IntValue(value = it.currentValue)

                it.copy(currentValue = newValue)
            }
            is UiControlsModel.Editable -> {
                val key = PREFIX + it.key

                val newValue: String = it.currentValue
                store[key] = StoreValue.StringValue(value = newValue)

                it.copy(currentValue = newValue)
            }
            is UiControlsModel.Choice -> {
                val key = PREFIX + it.key
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