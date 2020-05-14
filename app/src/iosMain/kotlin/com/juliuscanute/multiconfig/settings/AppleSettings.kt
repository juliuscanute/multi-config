package com.juliuscanute.multiconfig.settings

import platform.Foundation.NSUserDefaults

class AppleSettings(group: String) : Settings {

    private val defaults = NSUserDefaults(suiteName = group)

    override fun hasKey(key: String): Boolean {
        return defaults.objectForKey(defaultName = key) != null
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return defaults.boolForKey(defaultName = key)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        //TODO: Default value can't be used now.
        val value = defaults.integerForKey(defaultName = key)
        return value.toInt()
    }

    override fun getString(key: String, defaultValue: String): String {
        return defaults.stringForKey(defaultName = key) ?: defaultValue
    }

    override fun putBoolean(key: String, value: Boolean) {
        defaults.setBool(value = value, forKey = key)
        defaults.synchronize()
    }

    override fun putInt(key: String, value: Int) {
        defaults.setInteger(value = value.toLong(), forKey = key)
        defaults.synchronize()
    }

    override fun putString(key: String, value: String) {
        defaults.setObject(value = value, forKey = key)
        defaults.synchronize()
    }

    class Factory(val group: String) : Settings.Factory {
        override fun create(): Settings {
            return AppleSettings(group = group)
        }
    }
}