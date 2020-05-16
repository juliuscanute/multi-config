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
        let config: ConfigurationGetter = Environment.multiConfig!.getConfig()
        let text = config.getConfigString(userKey: "C")
        let visibility = config.getConfigBoolean(userKey: "A")
        let size = Int(config.getConfigInt(userKey: "B"))
        let currentChoice: KotlinPair = config.getConfigPair(userKey: "D")
        let choiceNo: Int = Int(currentChoice.second as! Int32)
        var color: Color = .blue
        switch choiceNo {
        case 0:
            color = .red
        case 1:
            color = .green
        default:
            color = .blue
        }
        rootView = AnyView(rootView.environmentObject(ConfigurationData(text: text, visibility: visibility, color: color, size: size)))
    }
}
