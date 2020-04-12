//
// Created by Julius Canute on 11/4/20.
//

import Foundation

enum ConfigurationDetailState {
    case initialState
    case loadEnvironmentConfiguration(LoadEnvironmentConfigurationState)
    case showChoiceConfiguration(ShowChoiceConfigurationState)
    case showEditableState(ShowEditableState)
}

struct LoadEnvironmentConfigurationState {
    let items: [ItemState]
}

struct ShowChoiceConfigurationState {
    let description: String
    let items: [ChoiceItem]
    let currentSelection: Int
    let key: String
}

struct ShowEditableState {
    let description: String
    let value: String
    let key: String
}