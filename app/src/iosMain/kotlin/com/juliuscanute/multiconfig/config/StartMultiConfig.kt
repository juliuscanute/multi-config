package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.settings.UserSettings
import platform.UIKit.UIViewController

fun startMultiConfig(rootGroup: String, body: BaseMultiConfig<UIViewController>.() -> Unit): Starter {
    val config = BaseMultiConfig<UIViewController>(settings = UserSettings().userSettings(rootGroup))
    config(body)
    return config
}

fun startMultiConfig(body: BaseMultiConfig<UIViewController>.() -> Unit): Starter {
    val config = BaseMultiConfig<UIViewController>()
    config(body)
    return config
}