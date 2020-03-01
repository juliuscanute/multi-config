package settings

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings

actual class UserSettings {
    fun userSettings(context: Context): Settings = AndroidSettings.Factory(context).create()
}