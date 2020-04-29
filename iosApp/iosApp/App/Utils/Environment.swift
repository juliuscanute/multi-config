//
//  Environment.swift
//  iosApp
//
//  Created by Julius Canute on 22/3/20.
//

import Foundation
import MultiConfigCommon

public func appConfig(apply closure: (AppConfigurationBuilder) -> Void) -> NSMutableArray {
    let builder = AppConfigurationBuilder()
    closure(builder)
    return builder.build()
}


