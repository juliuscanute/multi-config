package com.juliuscanute.sampleandroid

import android.app.Application
import android.content.Intent
import com.juliuscanute.multiconfig.config.startMultiConfig

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startMultiConfig(
            context = this,
            intent = Intent(this@SampleApp, MainActivity::class.java)
        ) {
            multiConfig(configuration = configuration())
        }
    }
}