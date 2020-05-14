//
//  Environment.swift
//  iosApp
//
//  Created by Julius Canute on 22/3/20.
//

import Foundation
import MultiConfigCommon

public func startMultiConfig(rootGroup: String, apply closure: @escaping (StartMultiConfig) -> Void) {
    StartMultiConfigKt.startMultiConfig(rootGroup: rootGroup, body: closure)
}

public func startMultiConfig(apply closure: @escaping (StartMultiConfig) -> Void) {
    StartMultiConfigKt.startMultiConfig(body: closure)
}

public func appConfig(apply closure: @escaping (AppConfigurationBuilder) -> Void) -> NSMutableArray {
    return AppConfigurationBuilderKt.appConfig(config: closure)
}

class MultiOSConfig{
    static func getConfig() -> ConfigurationGetter {
        return StartMultiConfigKt.getConfig() 
    }
}
