package settings

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings

actual class UserSettings {
    fun userSettings(): Settings = AppleSettings.Factory().create()
}