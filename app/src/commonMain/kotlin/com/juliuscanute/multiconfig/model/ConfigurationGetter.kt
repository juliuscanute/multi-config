package com.juliuscanute.multiconfig.model

interface ConfigurationGetter {
    fun getConfigInt(userKey: String): Int
    fun getConfigString(userKey: String): String
    fun getConfigBoolean(userKey: String): Boolean
    fun getConfigPair(userKey: String): Pair<String, Int>
}