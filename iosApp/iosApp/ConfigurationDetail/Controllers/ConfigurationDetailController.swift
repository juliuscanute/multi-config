//
// Created by Julius Canute on 11/4/20.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa
import app


public class ConfigurationDetailController: NiblessViewController {
    let disposeBag = DisposeBag()
    let environment: String
    let viewModel: ConfigurationDetailViewModel

    init(baseViewModel: BaseViewModel, environment: String, configurationViewModelFactory: ConfigurationDependencyContainer) {
        self.viewModel = configurationViewModelFactory.makeConfigurationDetailViewModel()
        self.environment = environment
        super.init(viewModel: baseViewModel)
    }

    public override func loadView() {
        self.view = ConfigurationDetailRootVIew(viewModel: viewModel)
        attachView()
    }

    public override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        viewModel.onNavigationAppear(environment: environment)
        viewModel.loadEnvironmentConfiguration(environment: environment)
    }
}

protocol ConfigurationDetailViewModelFactory {

    func makeConfigurationDetailViewModel() -> ConfigurationDetailViewModel
}
