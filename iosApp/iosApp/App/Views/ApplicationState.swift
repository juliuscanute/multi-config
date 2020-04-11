//
// Created by Julius Canute on 11/4/20.
//

import Foundation

enum ApplicationState {
    case initialState
    case navigationControlConfig(NavigationConfigurationState)
}

struct NavigationConfigurationState {
    let title: String
    let environment: String
}