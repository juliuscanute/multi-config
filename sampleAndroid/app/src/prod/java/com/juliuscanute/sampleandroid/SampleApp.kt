package com.juliuscanute.sampleandroid

import android.app.Application
import com.juliuscanute.multiconfig.config.startMultiConfig

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startMultiConfig {
            multiConfig(
                configuration = configuration()
            )
        }
    }
}