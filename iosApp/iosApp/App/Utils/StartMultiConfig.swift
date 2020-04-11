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
    var startClass : AnyClass?
    
    func appConfig(configuration: NSMutableArray, startController start: String) {
        configManager = ConfigurationManager(repository: configuration, settings: UserSettings().userSettings())
        startClass = NSClassFromString(start)
    }
    
    func getConfigurationManager() -> ConfigurationManager {
        configManager!
    }
    
    func getStartController() -> UIViewController {
        let viewClass = startClass as! UIViewController.Type
        return viewClass.init()
    }
}

public func startMultiConfig(apply closure: (StartMultiConfig) -> Void) -> StartMultiConfig {
    let config = StartMultiConfig()
    closure(config)
    return config
}


