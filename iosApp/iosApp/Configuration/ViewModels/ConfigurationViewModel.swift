//
//  ConfigurationViewModel.swift
//  iosApp
//
//  Created by Julius Canute on 29/3/20.
//

import Foundation
import app
import RxSwift
import RxCocoa

public class ConfigurationViewModel {
    let configManager: ConfigurationManager
    var state: Observable<ConfigurationState> {
        stateSubject.asObservable()
    }
    let configurationDetailResponder: ConfigurationDetailResponder
    private let stateSubject: PublishSubject<ConfigurationState> = PublishSubject<ConfigurationState>()
    public var launchText: Observable<String> {
        launchTextSubject.asObservable()
    }
    private let launchTextSubject:BehaviorSubject<String> = BehaviorSubject<String>(value: "")
    
    init(manager: ConfigurationManager, configurationDetailResponder: ConfigurationDetailResponder) {
        self.configManager = manager
        self.configurationDetailResponder = configurationDetailResponder
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
        launchTextSubject.onNext(applicationConfiguration[Int(selectedIndex)].environment)
        stateSubject.onNext(ConfigurationState.appConfig(appState))
    }
    
    func getAvailableConfigurationCount() -> Int {
        return configManager.getApplicationConfiguration().count
    }
    
    func getConfigurationForIndex(index: Int) -> Configuration {
        let applicationConfiguration: ApplicationConfiguration = configManager.getApplicationConfiguration() as! ApplicationConfiguration
        return applicationConfiguration[index]
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
