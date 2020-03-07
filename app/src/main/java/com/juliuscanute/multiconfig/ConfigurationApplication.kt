package com.juliuscanute.multiconfig

import android.app.Application
import com.juliuscanute.multiconfig.di.dependencyModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ConfigurationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ConfigurationApplication)
            modules(dependencyModule)
        }
    }
}