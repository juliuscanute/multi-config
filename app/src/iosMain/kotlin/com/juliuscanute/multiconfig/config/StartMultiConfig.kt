package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.settings.Settings

fun startMultiConfig(
    rootGroup: String,
    configViewController: MultiConfigViewController,
    body: BaseMultiConfig.() -> Unit
): ConfigBase {
    val launch = ApplicationLaunchController().setLaunchIntent(controller = configViewController)
    val config =
        BaseMultiConfig(settings = Settings(rootGroup), startController = launch)
    config(body)
    return config
}