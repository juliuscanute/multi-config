package com.juliuscanute.multiconfig.model

import com.juliuscanute.multiconfig.builder.EnvironmentConfiguration
import com.juliuscanute.multiconfig.settings.Settings

abstract class ConfigurationGetterImplementation(
    private val environment: String
) : ConfigurationGetter {
    companion object {
        const val PREFIX = "ImmutableConfigurationRepository"
        const val PAIR_SUFFIX_STRING = "PairSuffixString"
        const val PAIR_SUFFIX_INT = "PairSuffixInt"
    }

    protected val store: MutableMap<String, StoreValue>

    init {
        store = HashMap()
    }

    override fun getConfigInt(userKey: String): Int {
        val key = environment + PREFIX + userKey
        val storeValue: StoreValue? = store[key]
        checkNotNull(storeValue, { "Unable to find the key" })
        check(storeValue is StoreValue.IntValue) { "Store value must be Int" }
        return storeValue.value
    }

    override fun getConfigString(userKey: String): String {
        val key = environment + PREFIX + userKey
        val storeValue: StoreValue? = store[key]
        checkNotNull(storeValue, { "Unable to find the key" })
        check(storeValue is StoreValue.StringValue) { "Store value must be String" }
        return storeValue.value
    }

    override fun getConfigBoolean(userKey: String): Boolean {
        val key = environment + PREFIX + userKey
        val storeValue: StoreValue? = store[key]
        checkNotNull(storeValue, { "Unable to find the key" })
        check(storeValue is StoreValue.BooleanValue) { "Store value must be Boolean" }
        return storeValue.value
    }

    override fun getConfigPair(userKey: String): Pair<String, Int> {
        val key = environment + PREFIX + userKey
        val storeValue: StoreValue? = store[key]
        checkNotNull(storeValue, { "Unable to find the key" })
        check(storeValue is StoreValue.KeyValue) { "Store value must be Pair" }
        return storeValue.key to storeValue.value
    }

    abstract fun loadConfiguration(
        configs: EnvironmentConfiguration,
        settings: Settings? = null,
        environment: String
    ): List<UiControlsModel>
}