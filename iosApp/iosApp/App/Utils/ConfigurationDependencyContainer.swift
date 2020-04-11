//
//  ConfigurationDependencyContainer.swift
//  iosApp
//
//  Created by Julius Canute on 4/4/20.
//
import UIKit
import app

public class ConfigurationDependencyContainer {
    let configurationManager: ConfigurationManager
    let sharedMainViewModel: MainViewModel
    init() {
        configurationManager = startMultiConfig(apply: {
            $0.appConfig(configuration: setup(),startController: "ConfigurationController")
        }).getConfigurationManager()
        sharedMainViewModel = MainViewModel()
    }
    
    func makeMainViewController() -> MainViewController {
        let configurationViewController = makeConfigurationViewController()
        return MainViewController(viewModel: sharedMainViewModel,
                                 configurationController: configurationViewController)
    }
    
    func makeConfigurationViewController() -> ConfigurationController {
        ConfigurationController(configurationViewModelFactory: self)
    }
    
    func makeConfigurationViewModel() -> ConfigurationViewModel {
        ConfigurationViewModel(manager: configurationManager, configurationDetailResponder: sharedMainViewModel)
    }
}

extension ConfigurationDependencyContainer: ConfigurationViewModelFactory {}
