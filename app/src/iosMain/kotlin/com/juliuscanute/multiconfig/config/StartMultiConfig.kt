package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.settings.UserSettings

fun startMultiConfig(
    rootGroup: String,
    configViewController: MultiConfigViewController,
    body: BaseMultiConfig.() -> Unit
): ConfigBase {
    val launch = ApplicationLaunchController().setLaunchIntent(controller = configViewController)
    val config =
        BaseMultiConfig(settings = UserSettings().userSettings(rootGroup), startController = launch)
    config(body)
    return config
}