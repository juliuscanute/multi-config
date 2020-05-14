package com.juliuscanute.multiconfig.config

interface ApplicationLaunchController {
    fun<T> launchController(environment: String): T
}