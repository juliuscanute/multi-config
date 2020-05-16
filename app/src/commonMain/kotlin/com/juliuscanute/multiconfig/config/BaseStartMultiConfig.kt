package com.juliuscanute.multiconfig.config

fun startMultiConfig(body: BaseMultiConfig.() -> Unit): ConfigBase {
    val config = BaseMultiConfig()
    config(body)
    MultiConfigure(config)
    return config
}