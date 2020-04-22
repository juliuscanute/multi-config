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
    let rootGroup: String

    init(rootGroup: String) {
        self.rootGroup = rootGroup
    }

    public func appConfig(configuration: NSMutableArray) {
        configManager = ConfigurationManager(repository: configuration, settings: UserSettings().userSettings(group: rootGroup))
    }

    public func getConfigurationManager() -> ConfigurationManager {
        configManager!
    }
}

public func startMultiConfig(rootGroup: String, apply closure: (StartMultiConfig) -> Void) -> StartMultiConfig {
    let config = StartMultiConfig(rootGroup: rootGroup)
    MultiConfig.multiConfig = config
    closure(config)
    return config
}


public class MultiConfig {
    static var multiConfig: StartMultiConfig!

    static func getStartController(environment: String) -> UIViewController {
        (UIApplication.shared.delegate as! StartViewControllerInitializer).allocateStartViewController(environment: environment)
    }

    static public func getConfig() -> ConfigurationRepository {
        let configManager = multiConfig.configManager
        let applicationConfiguration: ApplicationConfiguration = configManager!.getApplicationConfiguration() as! ApplicationConfiguration
        let selectedConfig = configManager!.getConfig()
        let selectedIndex = selectedConfig < 0 ? 0 : selectedConfig
        let environment = applicationConfiguration[Int(selectedIndex)].environment
        return configManager!.getConfiguration(environment: environment)
    }

    static public func getConfig(environment: String) -> ConfigurationRepository {
        multiConfig.configManager!.getConfiguration(environment: environment)
    }
}


public protocol StartViewControllerInitializer {
    func allocateStartViewController(environment: String?) -> UIViewController
}
