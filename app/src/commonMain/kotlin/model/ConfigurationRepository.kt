package model

import builder.EnvironmentConfiguration
import builder.EnvironmentConfigurationImmutable
import settings.Settings

class ConfigurationRepository(
    private val configs: EnvironmentConfiguration,
    private val settings: Settings
) : ConfigurationGetterImplementation(configs, settings) {

    fun getEnvironmentConfiguration(): EnvironmentConfigurationImmutable = loadConfiguration(configs, settings)

    fun saveConfig(key: String, value: Int) {
        val userKey = PREFIX + key
        val found: UiControlsModel.Range? = configs.filterIsInstance<UiControlsModel.Range>()
            .firstOrNull { item -> item.key == key }
        checkNotNull(found, { "Unable to find the key" })
        found.currentValue = value
        store[userKey] = StoreValue.IntValue(value = value)
        settings.putInt(userKey, value)
    }

    fun saveConfig(key: String, value: String) {
        val userKey = PREFIX + key
        val found: UiControlsModel.Editable? = configs.filterIsInstance<UiControlsModel.Editable>()
            .firstOrNull { item -> item.key == key }
        checkNotNull(found, { "Unable to find the key" })
        found.currentValue = value
        store[userKey] = StoreValue.StringValue(value = value)
        settings.putString(userKey, value)
    }

    fun saveConfig(key: String, value: Boolean) {
        val userKey = PREFIX + key
        val found: UiControlsModel.Switch? = configs.filterIsInstance<UiControlsModel.Switch>()
            .firstOrNull { item -> item.key == key }
        checkNotNull(found, { "Unable to find the key" })
        found.switchValue = value
        store[userKey] = StoreValue.BooleanValue(value = value)
        settings.putBoolean(userKey, value)
    }

    fun saveConfig(key: String, value: Pair<String, Int>) {
        val userKey = PREFIX + key
        val found: UiControlsModel.Choice? = configs.filterIsInstance<UiControlsModel.Choice>()
            .firstOrNull { item -> item.key == key }
        checkNotNull(found, { "Unable to find the key" })
        found.items[value.second] = Item(information = value.first)
        found.currentChoiceIndex = value.second
        store[userKey] = StoreValue.KeyValue(key = value.first, value = value.second)
        settings.putString(userKey + PAIR_SUFFIX_STRING, value.first)
        settings.putInt(userKey + PAIR_SUFFIX_INT, value.second)
    }

    override fun loadConfiguration(configs: EnvironmentConfiguration, settings: Settings?) = configs.map {
        checkNotNull(settings){"settings must not be null"}
        when (it) {
            is UiControlsModel.Switch -> {
                val key = PREFIX + it.key
                val newValue: Boolean
                if (settings.hasKey(key)) {
                    newValue = settings.getBoolean(key, false)
                    store[key] = StoreValue.BooleanValue(value = newValue)
                } else {
                    newValue = it.switchValue
                    store[key] = StoreValue.BooleanValue(value = newValue)
                }
                it.copy(switchValue = newValue)
            }
            is UiControlsModel.Range -> {
                val key = PREFIX + it.key
                val newValue: Int
                if (settings.hasKey(key)) {
                    newValue = settings.getInt(key, 0)
                    store[key] = StoreValue.IntValue(value = newValue)
                } else {
                    newValue = it.currentValue
                    store[key] = StoreValue.IntValue(value = it.currentValue)
                }
                it.copy(currentValue = newValue)
            }
            is UiControlsModel.Editable -> {
                val key = PREFIX + it.key
                val newValue: String
                if (settings.hasKey(key)) {
                    newValue = settings.getString(key, "")
                    store[key] = StoreValue.StringValue(value = newValue)
                } else {
                    newValue = it.currentValue
                    store[key] = StoreValue.StringValue(value = newValue)
                }
                it.copy(currentValue = newValue)
            }
            is UiControlsModel.Choice -> {
                val key = PREFIX + it.key
                val keyString = PREFIX + it.key + PAIR_SUFFIX_STRING
                val keyInt = PREFIX + it.key + PAIR_SUFFIX_INT
                check(it.currentChoiceIndex < it.items.size) { "Choice mush be less than the available items" }
                val item = it.items[it.currentChoiceIndex]
                val newIntValue: Int
                val newStringValue: String
                val keyPresent =
                    settings.hasKey(keyString) and settings.hasKey(
                        keyInt
                    )

                if (keyPresent) {
                    newStringValue = settings.getString(keyString, "")
                    newIntValue = settings.getInt(keyInt, 0)
                    store[key] =
                        StoreValue.KeyValue(
                            key = newStringValue,
                            value = newIntValue
                        )
                } else {
                    newIntValue = it.currentChoiceIndex
                    store[key] =
                        StoreValue.KeyValue(
                            key = item.information,
                            value = it.currentChoiceIndex
                        )
                }
                it.copy(currentChoiceIndex = newIntValue)
            }
        }
    }.toList()
}