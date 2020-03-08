package model

import builder.EnvironmentConfiguration
import builder.EnvironmentConfigurationImmutable
import com.russhwolf.settings.Settings

class ConfigurationRepository(
    private val configs: EnvironmentConfiguration,
    private val settings: Settings
) : ConfigurationGetter {

    companion object {
        const val PREFIX = "ConfigurationRepository"
        const val PAIR_SUFFIX_STRING = "PairSuffixString"
        const val PAIR_SUFFIX_INT = "PairSuffixInt"
    }

    private val store: MutableMap<String, StoreValue>

    init {
        store = HashMap()
        loadConfiguration()
    }

    fun getEnvironmentConfiguration(): EnvironmentConfigurationImmutable = loadConfiguration()

    override fun getConfigInt(userKey: String): Int {
        val key = PREFIX + userKey
        val storeValue: StoreValue? = store[key]
        checkNotNull(storeValue, { "Unable to find the key" })
        val configValue: Int? = try {
            if (storeValue is StoreValue.IntValue) {
                storeValue.value
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        checkNotNull(configValue, { "Unable to get int value" })
        return configValue
    }

    fun saveConfig(key: String, value: Int) {
        val userKey = PREFIX + key
        val found: UiControlsModel.Range? = configs.filterIsInstance<UiControlsModel.Range>()
            .firstOrNull { item -> item.key == key }
        checkNotNull(found, { "Unable to find the key" })
        found.currentValue = value
        store[userKey] = StoreValue.IntValue(value = value)
        settings.putInt(userKey, value)
    }

    override fun getConfigString(userKey: String): String {
        val key = PREFIX + userKey
        val storeValue: StoreValue? = store[key]
        checkNotNull(storeValue, { "Unable to find the key" })
        val configValue: String? = try {
            if (storeValue is StoreValue.StringValue) {
                storeValue.value
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        checkNotNull(configValue, { "Unable to get string value" })
        return configValue
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

    override fun getConfigBoolean(userKey: String): Boolean {
        val key = PREFIX + userKey
        val storeValue: StoreValue? = store[key]
        checkNotNull(storeValue, { "Unable to find the key" })
        val configValue: Boolean? = try {
            if (storeValue is StoreValue.BooleanValue) {
                storeValue.value
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        checkNotNull(configValue, { "Unable to get boolean value" })
        return configValue
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

    override fun getConfigPair(userKey: String): Pair<String, Int> {
        val key = PREFIX + userKey
        val storeValue: StoreValue? = store[key]
        checkNotNull(storeValue, { "Unable to find the key" })
        val configValue: Pair<String, Int>? = try {
            if (storeValue is StoreValue.KeyValue) {
                storeValue.key to storeValue.value
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        checkNotNull(configValue, { "Unable to get boolean value" })
        return configValue
    }

    fun saveConfig(key: String, value: Pair<String, Int>) {
        val userKey = PREFIX + key
        val found: UiControlsModel.Choice? = configs.filterIsInstance<UiControlsModel.Choice>()
            .firstOrNull { item -> item.key == key }
        checkNotNull(found, { "Unable to find the key" })
        found.items[value.second] = Item(description = value.first)
        found.currentChoiceIndex = value.second
        store[userKey] = StoreValue.KeyValue(key = value.first, value = value.second)
        settings.putString(userKey + PAIR_SUFFIX_STRING, value.first)
        settings.putInt(userKey + PAIR_SUFFIX_INT, value.second)
    }

    private fun loadConfiguration() = configs.map {
        when (it) {
            is UiControlsModel.Switch -> {
                val key = PREFIX + it.key
                var newValue: Boolean = false
                if (settings.hasKey(key)) {
                    newValue = settings.getBoolean(key)
                    store[key] = StoreValue.BooleanValue(value = newValue)
                } else {
                    newValue = it.switchValue
                    store[key] = StoreValue.BooleanValue(value = newValue)
                }
                it.copy(switchValue = newValue)
            }
            is UiControlsModel.Range -> {
                val key = PREFIX + it.key
                var newValue: Int = 0
                if (settings.hasKey(key)) {
                    newValue = settings.getInt(key)
                    store[key] = StoreValue.IntValue(value = newValue)
                } else {
                    newValue = it.currentValue
                    store[key] = StoreValue.IntValue(value = it.currentValue)
                }
                it.copy(currentValue = newValue)
            }
            is UiControlsModel.Editable -> {
                val key = PREFIX + it.key
                var newValue: String = ""
                if (settings.hasKey(key)) {
                    newValue = settings.getString(key)
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
                var newIntValue: Int = 0
                var newStringValue: String = ""
                val keyPresent =
                    settings.hasKey(keyString) and settings.hasKey(
                        keyInt
                    )

                if (keyPresent) {
                    newStringValue = settings.getString(keyString)
                    newIntValue = settings.getInt(keyInt)
                    store[key] =
                        StoreValue.KeyValue(
                            key = newStringValue,
                            value = newIntValue
                        )
                } else {
                    newIntValue = it.currentChoiceIndex
                    store[key] =
                        StoreValue.KeyValue(
                            key = item.description,
                            value = it.currentChoiceIndex
                        )
                }
                it.copy(currentChoiceIndex = newIntValue)
            }
        }
    }.toList()
}

sealed class StoreValue {
    data class BooleanValue(val value: Boolean) : StoreValue()
    data class IntValue(val value: Int) : StoreValue()
    data class StringValue(val value: String) : StoreValue()
    data class KeyValue(val key: String, val value: Int) : StoreValue()
}