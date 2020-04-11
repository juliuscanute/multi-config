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
}

protocol ConfigurationDetailViewModelFactory {

    func makeConfigurationDetailViewModel() -> ConfigurationDetailViewModel
}
