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
            strongSelf.showChoiceDialog(choiceState: state)
        }).disposed(by: disposeBag)
        viewModel.editable.subscribe(onNext: { [weak self] state in
            guard let strongSelf = self else {
                return
            }
            strongSelf.showEditableDialog(editableState: state)
        }).disposed(by: disposeBag)
    }

    public override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        viewModel.onNavigationAppear(environment: environment)
        viewModel.loadEnvironmentConfiguration(environment: environment)
    }

    private func showEditableDialog(editableState: EditableState) {
        let alert = UIAlertController(title: NSLocalizedString("edit_configuration", comment: "Edit the configuration"), message: editableState.description, preferredStyle: UIAlertController.Style.alert)
        alert.addTextField { (inputText) in
            inputText.text = editableState.currentValue
        }
        alert.addAction(UIAlertAction(title: NSLocalizedString("ok", comment: "Save changes"), style: .default, handler: { [weak alert, weak self] (_) in
            guard let textField = alert?.textFields?[0].text, let strongSelf = self else {
                return
            }
            strongSelf.viewModel.saveStringConfiguration(key: editableState.key, currentValue: textField)
        }))
        alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: "Dont save changes"), style: UIAlertAction.Style.cancel, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }

    private func showChoiceDialog(choiceState: ChoiceState) {
        let alert = UIAlertController(title: NSLocalizedString("choose_configuration", comment: "Choose a configuration"), message: choiceState.description, preferredStyle: UIAlertController.Style.alert)
        let closure = { [weak alert, weak self](action: UIAlertAction) -> Void in
            guard let index = alert?.actions.firstIndex(of: action), let strongSelf = self else {
                return
            }
            strongSelf.viewModel.savePairConfiguration(key: choiceState.key, currentValue:
            (key: choiceState.items[index].description, value: Int32(index)))
        }
        choiceState.items.enumerated().forEach({ index, item in
            if index == choiceState.currentChoiceIndex {
                alert.addAction(UIAlertAction(title: item.description, style: UIAlertAction.Style.destructive, handler: closure))
            } else {
                alert.addAction(UIAlertAction(title: item.description, style: UIAlertAction.Style.default, handler: closure))
            }
        })
        alert.addAction(UIAlertAction(title: NSLocalizedString("cancel", comment: "Dont save changes"), style: UIAlertAction.Style.cancel, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
}

protocol ConfigurationDetailViewModelFactory {

    func makeConfigurationDetailViewModel() -> ConfigurationDetailViewModel
}
