//
//  StartMultiConfig.swift
//  iosApp
//
//  Created by Julius Canute on 24/3/20.
//

import UIKit
import Foundation
import app


public typealias ApplicationConfiguration = [Configuration]

public class StartMultiConfig {
    var settings: Settings?
    var configManager: ConfigurationManager?
    var startClass: AnyClass?

    public func appConfig(configuration: NSMutableArray, startController start: AnyClass) {
        configManager = ConfigurationManager(repository: configuration, settings: UserSettings().userSettings())
        startClass = start
    }

    public func getConfigurationManager() -> ConfigurationManager {
        configManager!
    }
}

public func startMultiConfig(apply closure: (StartMultiConfig) -> Void) -> StartMultiConfig {
    let config = StartMultiConfig()
    MultiConfig.multiConfig = config
    closure(config)
    return config
}


public class MultiConfig {
    static var multiConfig: StartMultiConfig!

    static func getStartController() -> UIViewController {
        let viewClass = multiConfig.startClass as! UIViewController.Type
        return viewClass.init()
    }

    static public func getConfig() -> ConfigurationRepository {
        let configManager = multiConfig.configManager
        let applicationConfiguration: ApplicationConfiguration = configManager!.getApplicationConfiguration() as! ApplicationConfiguration
        let selectedConfig = configManager!.getConfig()
        let selectedIndex = selectedConfig < 0 ? 0 : selectedConfig
        let environment = applicationConfiguration[Int(selectedIndex)].environment
        return configManager!.getConfiguration(environment: environment)
    }
}

