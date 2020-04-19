//
// Created by Julius Canute on 11/4/20.
//

import Foundation
import app
import RxSwift
import RxCocoa


public class BaseViewModel: ConfigurationChangeResponder {
    var state: Observable<ApplicationState> {
        stateSubject.asObservable()
    }
    private let stateSubject: BehaviorSubject<ApplicationState> = BehaviorSubject<ApplicationState>(value: .initialState)
    private let launchApplicationResponder: LaunchApplicationResponder
    private var environment: String? = nil

    init(launchApplicationResponder: LaunchApplicationResponder) {
        self.launchApplicationResponder = launchApplicationResponder
    }

    func onConfigurationChange(state: NavigationConfigurationState) {
        environment = state.environment
        stateSubject.onNext(.navigationControlConfig(state))
    }

    @objc func launchApplicationController() {
        launchApplicationResponder.launchConfiguration(environment: environment)
    }
}