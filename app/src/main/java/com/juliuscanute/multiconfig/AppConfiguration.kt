package com.juliuscanute.multiconfig

import builder.appConfig

fun setup() = appConfig {
    config("DEV") {

        switch {
            key = "A"
            description = "A-D"
            switchValue = false
        }

    }

    config("SIT") {
        range {
            key = "B"
            description = "B-D"
            min = 1
            max = 1000
            currentValue = 50
        }
    }

    config("UAT") {
        editable {
            key = "C"
            description = "C-D"
            currentValue = "C-V"
        }
    }

    config("PROD") {
        choice {
            key = "D"
            description = "D-D"
            currentChoiceIndex = 0
            item {
                description = "E"
            }
            item {
                description = "F"
            }
            item {
                description = "G"
            }
        }
    }
}