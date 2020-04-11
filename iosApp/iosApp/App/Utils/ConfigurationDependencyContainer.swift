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
    let sharedMainViewModel: NavigationViewModel
    let baseViewModel: BaseViewModel

    init() {
        configurationManager = startMultiConfig(apply: {
            $0.appConfig(configuration: setup(), startController: "ConfigurationController")
        }).getConfigurationManager()
        sharedMainViewModel = NavigationViewModel()
        baseViewModel = BaseViewModel()
    }

    func makeMainViewController() -> MainViewController {
        MainViewController(viewModel: sharedMainViewModel, dependencyContainer: self)
    }

    func makeConfigurationViewController() -> ConfigurationController {
        ConfigurationController(baseViewModel: baseViewModel, configurationViewModelFactory: self)
    }

    func makeConfigurationDetailViewController(environment: String) -> ConfigurationDetailController {
        ConfigurationDetailController(baseViewModel: baseViewModel, environment: environment, configurationViewModelFactory: self)
    }
}

extension ConfigurationDependencyContainer: ConfigurationViewModelFactory, ConfigurationDetailViewModelFactory {
    func makeConfigurationDetailViewModel() -> ConfigurationDetailViewModel {
        ConfigurationDetailViewModel(manager: configurationManager, configurationChangeResponder: baseViewModel)
    }


    func makeConfigurationViewModel() -> ConfigurationViewModel {
        ConfigurationViewModel(manager: configurationManager, configurationDetailResponder: sharedMainViewModel,
                configurationChangeResponder: baseViewModel)
    }
}
