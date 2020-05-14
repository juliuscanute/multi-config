package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.settings.UserSettings
import platform.UIKit.UIViewController

fun startMultiConfig(rootGroup: String, body: BaseMultiConfig<UIViewController>.() -> Unit) {
    val config = BaseMultiConfig<UIViewController>(settings = UserSettings().userSettings(rootGroup))
    config(body)
    MultiConfig(config)
}

fun startMultiConfig(body: BaseMultiConfig<UIViewController>.() -> Unit) {
    val config = BaseMultiConfig<UIViewController>()
    config(body)
    MultiConfig(config)
}