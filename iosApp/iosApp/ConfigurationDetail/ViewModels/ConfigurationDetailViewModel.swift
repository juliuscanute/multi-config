//
// Created by Julius Canute on 11/4/20.
//

import Foundation
import app
import RxSwift
import RxCocoa


public class ConfigurationDetailViewModel {
    let configManager: ConfigurationManager
    let configurationChangeResponder: ConfigurationChangeResponder
    var state: Observable<ConfigurationDetailState> {
        stateSubject.asObservable()
    }
    private let stateSubject: BehaviorSubject<ConfigurationDetailState> = BehaviorSubject<ConfigurationDetailState>(value: .initialState)

    init(manager: ConfigurationManager, configurationChangeResponder: ConfigurationChangeResponder) {
        self.configManager = manager
        self.configurationChangeResponder = configurationChangeResponder
    }

    func setInitialViewState(environment: String) {
        configurationChangeResponder.onConfigurationChange(state: NavigationConfigurationState(title: environment,
                environment: environment))
    }
}