//
//  ConfigurationDependencyContainer.swift
//  iosApp
//
//  Created by Julius Canute on 4/4/20.
//

import UIKit
import multiconfig_common

public class ConfigurationDependencyContainer {
    let configurationManager: ConfigurationManager
    let sharedMainViewModel: NavigationViewModel
    let baseViewModel: BaseViewModel

    init(configurationManager: ConfigurationManager) {
        self.configurationManager = configurationManager
        sharedMainViewModel = NavigationViewModel()
        baseViewModel = BaseViewModel(launchApplicationResponder: sharedMainViewModel)
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
