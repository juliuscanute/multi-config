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
        self.view = ConfigurationDetailRootView(viewModel: viewModel)
        attachView()
    }

    public override func viewDidLoad() {
        super.viewDidLoad()
        viewModel.choice.subscribe(onNext: { [weak self] state in
            guard let strongSelf = self else {
                return
            }
            strongSelf.showDialog(choiceState: state)
        }).disposed(by: disposeBag)
    }

    public override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        viewModel.onNavigationAppear(environment: environment)
        viewModel.loadEnvironmentConfiguration(environment: environment)
    }

    private func showDialog(choiceState: ChoiceState) {
        let alert = UIAlertController(title: "Choose Configuration", message: choiceState.description, preferredStyle: UIAlertController.Style.alert)
        let closure = { (action: UIAlertAction) -> Void in
            guard let index = alert.actions.index(of: action) else {
                return
            }
            self.viewModel.savePairConfiguration(key: choiceState.key, currentValue:
            (key: choiceState.items[index].description, value: Int32(index)))
        }
        choiceState.items.enumerated().forEach({ index, item in
            if index == choiceState.currentChoiceIndex {
                alert.addAction(UIAlertAction(title: item.description, style: UIAlertAction.Style.destructive, handler: closure))
            } else {
                alert.addAction(UIAlertAction(title: item.description, style: UIAlertAction.Style.default, handler: closure))
            }
        })
        alert.addAction(UIAlertAction(title: "Cancel", style: UIAlertAction.Style.cancel, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
}

protocol ConfigurationDetailViewModelFactory {

    func makeConfigurationDetailViewModel() -> ConfigurationDetailViewModel
}
