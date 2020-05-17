//
//  Environment.swift
//  sampleios
//
//  Created by Julius Canute on 16/5/20.
//  Copyright © 2020 Julius Canute. All rights reserved.
//

import Foundation
import SwiftUI
import MultiConfigCommon

class LaunchController: UIHostingController<AnyView>, MultiConfigViewController {
    func setEnvironment(environment: String) {
        Environment.configData.setData(environment: environment)
    }
}
