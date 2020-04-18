//
// Created by Julius Canute on 11/4/20.
//

import Foundation
import app
import RxSwift
import RxCocoa

public typealias EnvironmentConfiguration = [UiControlsModel]

public class ConfigurationDetailViewModel: ConfigurationSwitchCellDelegate, ConfigurationRangeCellDelegate {
    private let configManager: ConfigurationManager
    private let configurationChangeResponder: ConfigurationChangeResponder
    private var manager: ConfigurationRepository!
    var state: Observable<ConfigurationDetailState> {
        stateSubject.asObservable()
    }
    public var choice: Observable<ChoiceState> {
        choiceDialog.asObserver()
    }
    public var editable: Observable<EditableState> {
        editableDialog.asObserver()
    }
    private let stateSubject: BehaviorSubject<ConfigurationDetailState> = BehaviorSubject<ConfigurationDetailState>(value: .initialState)
    private let choiceDialog: PublishSubject<ChoiceState> = PublishSubject<ChoiceState>()
    private let editableDialog: PublishSubject<EditableState> = PublishSubject<EditableState>()

    init(manager: ConfigurationManager, configurationChangeResponder: ConfigurationChangeResponder) {
        self.configManager = manager
        self.configurationChangeResponder = configurationChangeResponder
    }

    func loadEnvironmentConfiguration(environment: String) {
        manager = configManager.getConfiguration(environment: environment)
        loadUpdatedState()
    }

    func saveBooleanConfiguration(key: String, currentValue: Bool) {
        manager.saveConfig(key: key, value: currentValue)
        loadUpdatedState()
    }

    func saveIntConfiguration(key: String, currentValue: Int) {
        manager.saveConfig(key: key, value_: Int32(currentValue))
        loadUpdatedState()
    }

    func savePairConfiguration(key: String, currentValue: (key: String, value: Int32)) {
        let currentPair = KotlinPair(first: currentValue.key, second: currentValue.value)
        manager.saveConfig(key: key, value__: currentPair)
        loadUpdatedState()
    }

    func saveStringConfiguration(key: String, currentValue: String) {
        manager.saveConfig(key: key, value___: currentValue)
        loadUpdatedState()
    }

    func showAvailableChoices(choiceState: ChoiceState) {
        choiceDialog.onNext(choiceState)
    }

    func showEditable(editableState: EditableState) {
        editableDialog.onNext(editableState)
    }

    private func loadUpdatedState() {
        let updatedConfig: EnvironmentConfiguration = manager.getEnvironmentConfiguration()
        stateSubject.onNext(ConfigurationDetailState.loadEnvironmentConfiguration(
                LoadEnvironmentConfigurationState(items: updatedConfig.mapState()
                )))
    }

    func onNavigationAppear(environment: String) {
        configurationChangeResponder
                .onConfigurationChange(state: NavigationConfigurationState(
                title: environment,
                buttonTitle: NSLocalizedString("selected_configuration", bundle: Bundle(for: ConfigurationDetailViewModel.self), comment: "Select application configuration"),
                backTitle: NSLocalizedString("configuration", bundle: Bundle(for: ConfigurationDetailViewModel.self), comment: "Application Title"),
                environment: environment))
    }
}

extension EnvironmentConfiguration {
    func mapState() -> [ItemState] {
        self.enumerated().map { (index: Int, configuration: UiControlsModel) in
            switch configuration {
            case is UiControlsModel.Switch:
                let uiSwitch: UiControlsModel.Switch = configuration as! UiControlsModel.Switch
                return ItemState.switchState(SwitchState(key: uiSwitch.key, description: uiSwitch.information,
                        switchValue: uiSwitch.switchValue))
            case is UiControlsModel.Range:
                let uiRange: UiControlsModel.Range = configuration as! UiControlsModel.Range
                return ItemState.rangeState(RangeState(key: uiRange.key,
                        description: uiRange.information, min: Int(uiRange.min), max: Int(uiRange.max),
                        currentValue: Int(uiRange.currentValue)))
            case is UiControlsModel.Editable:
                let uiEditable: UiControlsModel.Editable = configuration as! UiControlsModel.Editable
                return ItemState.editableState(EditableState(key: uiEditable.key,
                        description: uiEditable.information, currentValue: uiEditable.currentValue))
            default:
                let uiChoice: UiControlsModel.Choice = configuration as! UiControlsModel.Choice
                return ItemState.choiceState(ChoiceState(key: uiChoice.key,
                        description: uiChoice.information, currentChoiceIndex: Int(uiChoice.currentChoiceIndex),
                        items: uiChoice.items.map { (item: Any) in
                            ChoiceItem(description: (item as! Item).information)
                        }))
            }
        }
    }
}

