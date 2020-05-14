package com.juliuscanute.multiconfig.settings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class AndroidSetting private constructor(context: Context, name: String) :
    Settings {
    private var sharedPreference: SharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE)

    override fun hasKey(key: String): Boolean =
        sharedPreference.contains(key)

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        sharedPreference.getBoolean(key, defaultValue)

    override fun getInt(key: String, defaultValue: Int): Int =
        sharedPreference.getInt(key, defaultValue)

    override fun getString(key: String, defaultValue: String): String =
        sharedPreference.getString(key, defaultValue) ?: ""

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreference.edit()
            .putBoolean(key, value)
            .apply()
    }

    override fun putInt(key: String, value: Int) {
        sharedPreference.edit()
            .putInt(key, value)
            .apply()
    }

    override fun putString(key: String, value: String) {
        sharedPreference.edit()
            .putString(key, value)
            .apply()
    }

    class Factory(private val context: Context, private val name: String = "multi-config") : Settings.Factory {
        override fun create(): Settings {
            return AndroidSetting(context, name)
        }
    }
}