package settings


actual class UserSettings {
    fun userSettings(group: String): Settings = AppleSettings.Factory(group = group).create()
}