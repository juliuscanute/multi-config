//
// Created by Julius Canute on 16/5/20.
// Copyright (c) 2020 Julius Canute. All rights reserved.
//

import Foundation
import SwiftUI
import Combine
import MultiConfigCommon


final class ConfigurationData: ObservableObject {
    @Published var text: String = ""
    @Published var visibility: Bool = false
    @Published var color: Color = .black
    @Published var size: Int = 16
    
    func setData(environment: String? = nil) {
        if environment != nil {
            Environment.multiConfig?.setEnvironment(environment: environment!)
        }
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
        self.text = text
        self.color = color
        self.size = size
        self.visibility = visibility
    }
}
