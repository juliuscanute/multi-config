package com.juliuscanute.sampleandroid

import com.juliuscanute.multiconfig.builder.appConfig

fun configuration() = appConfig {
    config("PREMIUM") {
        switch {
            key = "A"
            description = "Set text visibility"
            switchValue = true
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
            currentValue = "Hello iOS!"
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
}