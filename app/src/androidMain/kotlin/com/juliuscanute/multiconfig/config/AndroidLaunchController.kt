package com.juliuscanute.multiconfig.config

import android.content.Intent

class AndroidLaunchController(private val intent: Intent) : LaunchController<Intent> {
    companion object {
        const val ENVIRONMENT = "ENVIRONMENT"
    }

    override fun launchController(environment: String): Intent {
        return intent.apply {
            putExtra(ENVIRONMENT, environment)
        }
    }
}