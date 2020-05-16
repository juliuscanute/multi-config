package com.juliuscanute.multiconfig.config

import android.content.Context
import android.content.Intent
import com.juliuscanute.multiconfig.settings.UserSettings

fun startMultiConfig(context: Context, intent: Intent, body: BaseMultiConfig.() -> Unit) {
    val launch = ApplicationLaunchController().setLaunchIntent(intent = intent)
    val config = BaseMultiConfig(settings = UserSettings().userSettings(context), startController = launch)
    config(body)
    MultiConfigure(config)
}