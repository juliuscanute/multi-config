package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.settings.UserSettings
import platform.UIKit.UIViewController

fun startMultiConfig(
    rootGroup: String,
    configViewController: MultiConfigViewController,
    body: BaseMultiConfig<UIViewController>.() -> Unit
): ConfigBase {
    val launch = ApplicationLaunchController().setLaunchIntent(controller = configViewController)
    val config =
        BaseMultiConfig<UIViewController>(settings = UserSettings().userSettings(rootGroup), startController = launch)
    config(body)
    return config
}

fun startMultiConfig(body: BaseMultiConfig<UIViewController>.() -> Unit): ConfigBase {
    val config = BaseMultiConfig<UIViewController>()
    config(body)
    return config
}