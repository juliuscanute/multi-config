package com.juliuscanute.multiconfig.config

import android.content.Context
import android.content.Intent
import com.juliuscanute.multiconfig.settings.Settings

fun startMultiConfig(context: Context, intent: Intent, body: BaseMultiConfig.() -> Unit) {
    val launch = ApplicationLaunchController().setLaunchIntent(intent = intent)
    val config = BaseMultiConfig(settings = Settings(context = context), startController = launch)
    config(body)
    MultiConfigure(config)
}