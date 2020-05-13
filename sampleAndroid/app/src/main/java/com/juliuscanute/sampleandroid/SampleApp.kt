package com.juliuscanute.sampleandroid

import android.app.Application
import android.content.Intent
import com.juliuscanute.multiconfig.di.StartMultiConfig
import com.juliuscanute.multiconfig.di.startMultiConfig
import model.ConfigurationManager

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startMultiConfig(context = this) {
            appConfig(configuration = builder.appConfig {
                config("SIT") {
                    switch {
                        key = "A"
                        description = "Set text visibility"
                        switchValue = false
                    }

                    range {
                        key = "B"
                        description = "Set text size"
                        min = 16
                        max = 72
                        currentValue = 50
                    }

                    editable {
                        key = "C"
                        description = "Set current text"
                        currentValue = "Hello World!"
                    }

                    choice {
                        key = "D"
                        description = "Set text color"
                        currentChoiceIndex = 0
                        item {
                            description = "RED"
                        }
                        item {
                            description = "GREEN"
                        }
                        item {
                            description = "BLUE"
                        }
                    }
                }
                config("UAT") {
                    switch {
                        key = "A"
                        description = "Set text visibility"
                        switchValue = false
                    }

                    range {
                        key = "B"
                        description = "Set text size"
                        min = 16
                        max = 72
                        currentValue = 50
                    }

                    editable {
                        key = "C"
                        description = "Set current text"
                        currentValue = "Hello World!"
                    }

                    choice {
                        key = "D"
                        description = "Set text color"
                        currentChoiceIndex = 0
                        item {
                            description = "RED"
                        }
                        item {
                            description = "GREEN"
                        }
                        item {
                            description = "BLUE"
                        }
                    }
                }
            }, intent = Intent(this@SampleApp, MainActivity::class.java))
        }
    }
}