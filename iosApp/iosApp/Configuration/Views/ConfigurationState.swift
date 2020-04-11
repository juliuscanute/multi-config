//
//  ConfigurationState.swift
//  iosApp
//
//  Created by Julius Canute on 29/3/20.
//

import Foundation

enum ConfigurationState {
    case initialState
    case appConfig(LoadApplicationConfigurationState)
    case selectedConfig(String)
    case buttonConfig(ButtonConfigurationState)
}

struct ButtonConfigurationState {
    let environment: String
}

struct LoadApplicationConfigurationState {
    let items: [ConfigurationViewDataModel]
    let environment: String
    let selectedIndex: Int
}

struct ConfigurationViewDataModel {
    let index: Int
    let environment: String
    let selected: Bool
}
