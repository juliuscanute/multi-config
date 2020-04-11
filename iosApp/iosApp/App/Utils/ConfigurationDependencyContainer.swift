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
    let sharedMainViewModel: SharedViewModel
    let baseViewModel: BaseViewModel

    init() {
        configurationManager = startMultiConfig(apply: {
            $0.appConfig(configuration: setup(), startController: "ConfigurationController")
        }).getConfigurationManager()
        sharedMainViewModel = SharedViewModel()
        baseViewModel = BaseViewModel()
    }

    func makeMainViewController() -> MainViewController {
        let configurationViewController = makeConfigurationViewController()
        return MainViewController(viewModel: sharedMainViewModel,
                configurationController: configurationViewController)
    }

    func makeConfigurationViewController() -> ConfigurationController {
        ConfigurationController(baseViewModel: baseViewModel, configurationViewModelFactory: self)
    }
}

extension ConfigurationDependencyContainer: ConfigurationViewModelFactory {

    func makeConfigurationViewModel() -> ConfigurationViewModel {
        ConfigurationViewModel(manager: configurationManager, configurationDetailResponder: sharedMainViewModel,
                configurationChangeResponder: baseViewModel)
    }
}
