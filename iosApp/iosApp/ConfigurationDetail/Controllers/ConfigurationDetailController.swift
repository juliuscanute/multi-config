//
// Created by Julius Canute on 11/4/20.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa
import app


public class ConfigurationDetailController: NiblessViewController {
//    let disposeBag = DisposeBag()
//    var appConfig: [ConfigurationViewDataModel]?
//    let viewModel: ConfigurationViewModel
//    let launchButton: UIButton = {
//        let button = UIButton(type: .system)
//        return button
//    }()
//
//    let stackView: UIStackView = {
//        let stackView: UIStackView = UIStackView()
//        stackView.distribution = .fill
//        stackView.alignment = .top
//        stackView.axis = .horizontal
//        stackView.spacing = 8.0
//        return stackView
//    }()
//
//    init(configurationViewModelFactory: ConfigurationDependencyContainer) {
//        self.viewModel = configurationViewModelFactory.makeConfigurationViewModel()
//        super.init(viewModelFactory: configurationViewModelFactory)
//    }
//
//    public override func loadView() {
//        self.view = ConfigurationRootView(viewModel: viewModel)
//        stackView.frame = self.navigationController?.toolbar.frame ?? .zero
//        stackView.addArrangedSubview(launchButton)
//        self.toolbarItems = [UIBarButtonItem(customView: buildToolbarView())]
//    }
//
//    override public func viewDidLoad() {
//        super.viewDidLoad()
//        navigationItem.title = NSLocalizedString("configuration", comment: "Application Title")
//        viewModel.loadApplicationConfiguration()
//    }
//
//    private func buildToolbarView() -> UIStackView {
//        let stackView = UIStackView(frame: self.navigationController?.toolbar.frame ?? .zero)
//        stackView.distribution = .fill
//        stackView.alignment = .top
//        stackView.axis = .horizontal
//        stackView.addArrangedSubview(launchButton)
//        stackView.spacing = 8.0
//        return stackView
//    }
}

protocol ConfigurationDetailViewModelFactory {

    func makeConfigurationDetailViewModel() -> ConfigurationDetailViewModel
}
