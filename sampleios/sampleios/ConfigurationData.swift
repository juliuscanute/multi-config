//
// Created by Julius Canute on 16/5/20.
// Copyright (c) 2020 Julius Canute. All rights reserved.
//

import Foundation
import SwiftUI
import Combine


final class ConfigurationData: ObservableObject {
    @Published var text: String = ""
    @Published var visibility: Bool = false
    @Published var color: Color = .black
    @Published var size: Int = 16
}
