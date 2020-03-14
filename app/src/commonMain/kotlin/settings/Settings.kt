package settings

interface Settings {

    fun hasKey(key: String): Boolean

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun getInt(key: String, defaultValue: Int): Int

    fun getString(key: String, defaultValue: String): String

    fun putBoolean(key: String, value: Boolean)

    fun putInt(key: String, value: Int)

    fun putString(key: String, value: String)

    interface Factory {
        fun create(): Settings
    }
}