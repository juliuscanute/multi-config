package settings


actual class UserSettings {
    fun userSettings(): Settings = AppleSettings.Factory().create()
}