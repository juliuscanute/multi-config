//
//  Environment.swift
//  iosApp
//
//  Created by Julius Canute on 22/3/20.
//

import Foundation
import MultiConfigCommon

public typealias ApplicationConfiguration = [Configuration]

public func startMultiConfig(rootGroup: String, controller: MultiConfigViewController, apply closure: @escaping (BaseMultiConfig) -> Void) -> MultiConfigure {
    let starter = StartMultiConfigKt.startMultiConfig(rootGroup: rootGroup, configViewController: controller, body: closure)
    return MultiConfigure(starter: starter)
}

public func startMultiConfig(apply closure: @escaping (BaseMultiConfig) -> Void) -> MultiConfigure {
    let starter = StartMultiConfigKt.startMultiConfig(body: closure)
    return MultiConfigure(starter: starter)
}

public func appConfig(apply closure: @escaping (AppConfigurationBuilder) -> Void) -> NSMutableArray {
    AppConfigurationBuilderKt.appConfig(config: closure)
}
