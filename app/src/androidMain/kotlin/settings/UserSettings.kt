package settings

import android.content.Context

actual class UserSettings {
    fun userSettings(context: Context): Settings = AndroidSetting.AndroidFactory(context).create()
}