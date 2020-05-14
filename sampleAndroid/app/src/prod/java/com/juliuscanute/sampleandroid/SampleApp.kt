package com.juliuscanute.sampleandroid

import android.app.Application
import com.juliuscanute.multiconfig.di.startMultiConfig

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startMultiConfig {
            appConfig(
                configuration = configuration()
            )
        }
    }
}