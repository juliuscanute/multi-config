package com.juliuscanute.multiconfig.config

interface LaunchController<T> {
    fun launchController(environment: String): T
}