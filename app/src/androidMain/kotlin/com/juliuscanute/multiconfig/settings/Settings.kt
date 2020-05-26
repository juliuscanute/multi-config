package com.juliuscanute.multiconfig.settings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

actual class Settings(context: Context, name: String = "multi-config") {
    private var sharedPreference: SharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE)

    actual fun hasKey(key: String): Boolean =
        sharedPreference.contains(key)

    actual fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        sharedPreference.getBoolean(key, defaultValue)

    actual fun getInt(key: String, defaultValue: Int): Int =
        sharedPreference.getInt(key, defaultValue)

    actual fun getString(key: String, defaultValue: String): String =
        sharedPreference.getString(key, defaultValue) ?: ""

    actual fun putBoolean(key: String, value: Boolean) {
        sharedPreference.edit()
            .putBoolean(key, value)
            .apply()
    }

    actual fun putInt(key: String, value: Int) {
        sharedPreference.edit()
            .putInt(key, value)
            .apply()
    }

    actual fun putString(key: String, value: String) {
        sharedPreference.edit()
            .putString(key, value)
            .apply()
    }
}