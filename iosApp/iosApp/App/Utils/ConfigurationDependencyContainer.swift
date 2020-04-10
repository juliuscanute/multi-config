//
//  ConfigurationDependencyContainer.swift
//  iosApp
//
//  Created by Julius Canute on 4/4/20.
//

import Foundation
import app

public class ConfiugrationDependencyContainer {
    let configurationManager: ConfigurationManager
    let sharedMainViewModel: MainViewModel
    init() {
        configurationManager = startMultiConfig(apply: {
            $0.appConfig(configuration: setup(),startController: "ConfigurationController")
        }).getConigurationManager()
        sharedMainViewModel = MainViewModel()
    }
    
    func makeMainViewController() -> MainViewConroller {
        let configurationViewController = makeConfigurationViewController()
        return MainViewConroller(viewModel: sharedMainViewModel,
                                 configurationController: configurationViewController)
    }
    
    func makeConfigurationViewController() -> ConfigurationViewController {
        return ConfigurationViewController(configurationViewModelFactory: self)
    }
    
    func makeConfigurationViewModel() -> ConfigurationViewModel {
        return ConfigurationViewModel(manager: configurationManager)
    }
}

extension ConfiugrationDependencyContainer: ConfigurationViewModelFactory {}
