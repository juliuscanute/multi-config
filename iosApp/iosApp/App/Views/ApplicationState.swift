//
// Created by Julius Canute on 11/4/20.
//

import Foundation

enum ApplicationState {
    case initialState
    case buttonConfig(ButtonConfigurationState)
}

struct ButtonConfigurationState {
    let environment: String
}