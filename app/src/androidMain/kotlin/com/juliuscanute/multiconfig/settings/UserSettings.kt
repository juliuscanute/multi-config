package com.juliuscanute.multiconfig.settings

import android.content.Context

actual class UserSettings {
    fun userSettings(context: Context): Settings = AndroidSetting.Factory(context).create()
}