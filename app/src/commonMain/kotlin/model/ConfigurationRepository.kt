package model

import builder.EnvironmentConfiguration
import builder.EnvironmentConfigurationImmutable
import com.russhwolf.settings.Settings

class ConfigurationRepository(
    private val configs: EnvironmentConfiguration,
    private val settings: Settings
) {

    companion object {
        const val PREFIX = "ConfigurationRepository"
        const val PAIR_SUFFIX_STRING = "PairSuffixString"
        const val PAIR_SUFFIX_INT = "PairSuffixInt"
    }

    private val store: MutableMap<String, StoreValue>

    init {
        store = HashMap()
        configs.forEach {
            when (it) {
                is UiControlsModel.Switch -> {
                    val key = PREFIX + it.key
                    if (settings.hasKey(key))
                        store[key] = StoreValue.BooleanValue(value = settings.getBoolean(key))
                    else
                        store[key] = StoreValue.BooleanValue(value = it.switchValue)
                }
                is UiControlsModel.Range -> {
                    val key = PREFIX + it.key
                    if (settings.hasKey(key))
                        store[key] = StoreValue.IntValue(value = settings.getInt(key))
                    else
                        store[key] = StoreValue.IntValue(value = it.currentValue)
                }
                is UiControlsModel.Editable -> {
                    val key = PREFIX + it.key
                    if (settings.hasKey(key))
                        store[key] = StoreValue.StringValue(value = settings.getString(key))
                    else
                        store[key] = StoreValue.StringValue(value = it.currentValue)
                }
                is UiControlsModel.Choice -> {
                    val key = PREFIX + it.key
                    val keyString = PREFIX + it.key + PAIR_SUFFIX_STRING
                    val keyInt = PREFIX + it.key + PAIR_SUFFIX_INT
                    check(it.currentChoiceIndex < it.items.size) { "Choice mush be less than the available items" }
                    val item = it.items[it.currentChoiceIndex]

                    val keyPresent =
                        settings.hasKey(keyString) and settings.hasKey(
                            keyInt
                        )

                    if (keyPresent)
                        store[key] =
                            StoreValue.KeyValue(
                                key = settings.getString(keyString),
                                value = settings.getInt(keyInt)
                            )
                    else
                        store[key] =
                            StoreValue.KeyValue(
                                key = item.description,
                                value = it.currentChoiceIndex
                            )
                }
            }
        }
    }

    fun getEnvironmentConfiguration(): EnvironmentConfigurationImmutable = configs.toList()

    fun getConfigInt(userKey: String): Int {
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

    fun getConfigString(userKey: String): String {
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

    fun getConfigBoolean(userKey: String): Boolean {
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

    fun getConfigPair(userKey: String): Pair<String, Int> {
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
}

sealed class StoreValue {
    data class BooleanValue(val value: Boolean) : StoreValue()
    data class IntValue(val value: Int) : StoreValue()
    data class StringValue(val value: String) : StoreValue()
    data class KeyValue(val key: String, val value: Int) : StoreValue()
}