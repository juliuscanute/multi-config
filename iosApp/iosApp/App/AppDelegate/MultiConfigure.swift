//
// Created by Julius Canute on 15/5/20.
// Copyright (c) 2020 Julius Canute. All rights reserved.
//

import Foundation
import MultiConfigCommon
import UIKit

public class MultiConfigure {
    let starter: Starter
    let dependencyInjectionContainer: ConfigurationDependencyContainer

    init(starter: Starter) {
        self.starter = starter
        self.dependencyInjectionContainer = ConfigurationDependencyContainer(starter: starter)
    }

    func getConfigurationManager() -> ConfigurationManager {
        starter.getConfigurationManager()
    }

    func getConfig() -> ConfigurationGetter {
        starter.getConfig()
    }

    func getLaunchController() -> ApplicationLaunchController {
        starter.getLaunchController()
    }

    func getEnvironment() -> String {
        starter.getEnvironment()
    }

    func setEnvironment(environment: String) {
        starter.setEnvironment(environment: environment)
    }

    func getRootViewController() -> UINavigationController {
        dependencyInjectionContainer.makeMainViewController()
    }
}
