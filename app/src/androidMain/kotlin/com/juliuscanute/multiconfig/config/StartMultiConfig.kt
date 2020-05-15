package com.juliuscanute.multiconfig.config

import android.content.Context
import android.content.Intent
import com.juliuscanute.multiconfig.settings.UserSettings

fun startMultiConfig(context: Context, body: BaseMultiConfig<Intent>.() -> Unit) {
    val config = BaseMultiConfig<Intent>(settings = UserSettings().userSettings(context))
    config(body)
    MultiConfigure(config)
}

fun startMultiConfig(body: BaseMultiConfig<Intent>.() -> Unit) {
    val config = BaseMultiConfig<Intent>()
    config(body)
    MultiConfigure(config)
}