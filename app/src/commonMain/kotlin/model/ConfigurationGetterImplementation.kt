package model

import builder.EnvironmentConfiguration
import settings.Settings

abstract class ConfigurationGetterImplementation(private val configs: EnvironmentConfiguration,
                                                 private var settings: Settings? = null
) : ConfigurationGetter {
    companion object {
        const val PREFIX = "ImmutableConfigurationRepository"
        const val PAIR_SUFFIX_STRING = "PairSuffixString"
        const val PAIR_SUFFIX_INT = "PairSuffixInt"
    }
    protected val store: MutableMap<String, StoreValue>

    init {
        store = HashMap()
        loadConfiguration(configs, settings)
    }
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

    abstract fun loadConfiguration(configs: EnvironmentConfiguration, settings: Settings? = null) :List<UiControlsModel>
}