package com.juliuscanute.multiconfig.config

import android.content.Intent

actual class ApplicationLaunchController {
    fun setLaunchIntent(intent: Intent): LaunchController<*> = AndroidLaunchController(intent = intent)
}