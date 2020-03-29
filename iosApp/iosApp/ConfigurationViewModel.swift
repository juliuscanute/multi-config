//
//  ConfigurationViewModel.swift
//  iosApp
//
//  Created by Julius Canute on 29/3/20.
//

import Foundation
import app

public class ConfigurationViewModel {
    let configManager: ConfigurationManager
    let state = Box<ConfigurationState?>(nil)
    
    init(manager: () -> ConfigurationManager?) {
        self.configManager = manager()!
    }
    
    func moveToConfigurationDetail(environment: String) {
        
    }
    
    func selectNewConfiguration(selected: Int) {
        configManager.saveConfig(value: Int32(selected))
        loadApplicationConfiguration()
    }
    
    func loadApplicationConfiguration() {
        let applicationConfiguration: ApplicationConfiguration = configManager.getApplicationConfiguration() as! ApplicationConfiguration
        let selectedConfig = configManager.getConfig()
        let selectedIndex = selectedConfig < 0 ? 0 : selectedConfig
        let appState = LoadApplicationConfigurationState(items: applicationConfiguration.mapState(selectedIndex: Int(selectedIndex)), environment: applicationConfiguration[Int(selectedIndex)].environment, selectedIndex: Int(selectedIndex))
        state.value = ConfigurationState.appConfig(appState)
    }
}

extension ApplicationConfiguration {
    func mapState(selectedIndex: Int = 0) -> [ConfigurationViewDataModel] {
        return self.enumerated().map{ (index: Int, configuration: Configuration) in
            if(selectedIndex == index) {
                return ConfigurationViewDataModel(index: index, environment: configuration.environment, selected: true)
            } else {
                return ConfigurationViewDataModel(index: index, environment: configuration.environment, selected: false)
            }
        }
    }
}
