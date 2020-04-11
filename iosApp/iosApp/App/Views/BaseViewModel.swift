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

    func onConfigurationChange(state: NavigationConfigurationState) {
        stateSubject.onNext(.navigationControlConfig(state))
    }
}