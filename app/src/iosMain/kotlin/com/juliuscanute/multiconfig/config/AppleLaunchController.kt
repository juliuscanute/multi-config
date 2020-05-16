package com.juliuscanute.multiconfig.config

class AppleLaunchController(private val controller: MultiConfigViewController) :
    LaunchController<MultiConfigViewController> {
    override fun launchController(environment: String): MultiConfigViewController {
        controller.setEnvironment(environment = environment)
        return controller
    }
}