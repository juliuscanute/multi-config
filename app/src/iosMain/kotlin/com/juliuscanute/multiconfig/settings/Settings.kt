package com.juliuscanute.multiconfig.settings

import platform.Foundation.NSUserDefaults

actual class Settings(group: String) {
    private val defaults = NSUserDefaults(suiteName = group)

    actual fun hasKey(key: String): Boolean {
        return defaults.objectForKey(defaultName = key) != null
    }

    actual fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return defaults.boolForKey(defaultName = key)
    }

    actual fun getInt(key: String, defaultValue: Int): Int {
        //TODO: Default value can't be used now.
        val value = defaults.integerForKey(defaultName = key)
        return value.toInt()
    }

    actual fun getString(key: String, defaultValue: String): String {
        return defaults.stringForKey(defaultName = key) ?: defaultValue
    }

    actual fun putBoolean(key: String, value: Boolean) {
        defaults.setBool(value = value, forKey = key)
        defaults.synchronize()
    }

    actual fun putInt(key: String, value: Int) {
        defaults.setInteger(value = value.toLong(), forKey = key)
        defaults.synchronize()
    }

    actual fun putString(key: String, value: String) {
        defaults.setObject(value = value, forKey = key)
        defaults.synchronize()
    }
}