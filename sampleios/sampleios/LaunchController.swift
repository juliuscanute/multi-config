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

class LaunchController: UIHostingController<ContentView>, MultiConfigViewController {
    func setEnvironment(environment: String) {
        let config: ConfigurationGetter = Environment.multiConfig!.getConfig()
        rootView.text = config.getConfigString(userKey: "C")
        rootView.visibility = config.getConfigBoolean(userKey: "A")
        rootView.size = Int(config.getConfigInt(userKey: "B"))
        let currentChoice: KotlinPair = config.getConfigPair(userKey: "D")
        let choiceNo: Int = Int(currentChoice.second as! Int32)
        switch choiceNo {
        case 0:
            rootView.color = .red
        case 1:
            rootView.color = .green
        default:
            rootView.color = .blue
        }
    }
}
