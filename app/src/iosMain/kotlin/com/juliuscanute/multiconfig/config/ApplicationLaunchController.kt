package com.juliuscanute.multiconfig.config

actual class ApplicationLaunchController {
    fun setLaunchIntent(controller: MultiConfigViewController): LaunchController<*> =
        AppleLaunchController(controller = controller)
}