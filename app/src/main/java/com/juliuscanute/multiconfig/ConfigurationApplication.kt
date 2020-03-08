package com.juliuscanute.multiconfig

import android.app.Application
import android.content.Intent
import com.juliuscanute.multiconfig.di.DependencyHandler

class ConfigurationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyHandler(context = this) {
            appConfig(configuration = setup(), intent = Intent())
        }
    }
}