package com.juliuscanute.sampleandroid

import android.app.Application
import android.content.Intent
import com.juliuscanute.multiconfig.builder.appConfig
import com.juliuscanute.multiconfig.config.startMultiConfig

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startMultiConfig(context = this) {
            appConfig(
                configuration = configuration(),
                intent = Intent(this@SampleApp, MainActivity::class.java)
            )
        }
    }
}