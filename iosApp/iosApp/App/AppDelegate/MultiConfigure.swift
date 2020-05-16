//
// Created by Julius Canute on 15/5/20.
// Copyright (c) 2020 Julius Canute. All rights reserved.
//

import Foundation
import MultiConfigCommon
import UIKit

public class MultiConfigure {
    let starter: ConfigBase
    let dependencyInjectionContainer: ConfigurationDependencyContainer

    init(starter: ConfigBase) {
        self.starter = starter
        self.dependencyInjectionContainer = ConfigurationDependencyContainer(starter: starter)
    }

    public func getConfigurationManager() -> ConfigurationManager {
        starter.getConfigurationManager()
    }

    public func getConfig() -> ConfigurationGetter {
        starter.getConfig()
    }

    public func getLaunchController() -> LaunchController {
        starter.getLaunchController()
    }

    public func getEnvironment() -> String {
        starter.getEnvironment()
    }

    public func setEnvironment(environment: String) {
        starter.setEnvironment(environment: environment)
    }

    public func getRootViewController() -> UINavigationController {
        dependencyInjectionContainer.makeMainViewController()
    }
}
