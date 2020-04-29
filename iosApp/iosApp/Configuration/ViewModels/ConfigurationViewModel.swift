//
//  ConfigurationViewModel.swift
//  iosApp
//
//  Created by Julius Canute on 29/3/20.
//

import Foundation
import multiconfig_common
import RxSwift
import RxCocoa

public class ConfigurationViewModel {
    let configManager: ConfigurationManager
    var state: Observable<ConfigurationState> {
        stateSubject.asObservable()
    }
    let configurationDetailResponder: ConfigurationDetailResponder
    let configurationChangeResponder: ConfigurationChangeResponder
    private let stateSubject: BehaviorSubject<ConfigurationState> = BehaviorSubject<ConfigurationState>(value: .initialState)

    init(manager: ConfigurationManager, configurationDetailResponder: ConfigurationDetailResponder,
         configurationChangeResponder: ConfigurationChangeResponder) {
        self.configManager = manager
        self.configurationDetailResponder = configurationDetailResponder
        self.configurationChangeResponder = configurationChangeResponder
    }

    func moveToConfigurationDetail(environment: String) {
        configurationDetailResponder.selectConfiguration(environment: environment)
    }

    func selectNewConfiguration(selected: Int) {
        configManager.saveConfig(value: Int32(selected))
        loadApplicationConfiguration()
    }

    func loadApplicationConfiguration() {
        let applicationConfiguration: ApplicationConfiguration = configManager.getApplicationConfiguration() as! ApplicationConfiguration
        let selectedConfig = configManager.getConfig()
        let selectedIndex = selectedConfig < 0 ? 0 : selectedConfig
        let environment = applicationConfiguration[Int(selectedIndex)].environment
        let appState = LoadApplicationConfigurationState(items: applicationConfiguration.mapState(selectedIndex: Int(selectedIndex)),
                environment: environment, selectedIndex: Int(selectedIndex))
        configurationChangeResponder
                .onConfigurationChange(state: NavigationConfigurationState(
                title: AppString.configuration,
                buttonTitle: AppString.selected_configuration,
                environment: environment))
        stateSubject.onNext(.appConfig(appState))
    }

    func getAvailableConfigurationCount() -> Int {
        configManager.getApplicationConfiguration().count
    }

    func getConfigurationForIndex(index: Int) -> Configuration {
        let applicationConfiguration: ApplicationConfiguration = configManager.getApplicationConfiguration() as! ApplicationConfiguration
        return applicationConfiguration[index]
    }
}

extension ApplicationConfiguration {
    func mapState(selectedIndex: Int = 0) -> [ConfigurationViewDataModel] {
        self.enumerated().map { (index: Int, configuration: Configuration) in
            if (selectedIndex == index) {
                return ConfigurationViewDataModel(index: index, environment: configuration.environment, selected: true)
            } else {
                return ConfigurationViewDataModel(index: index, environment: configuration.environment, selected: false)
            }
        }
    }
}
