//
// Created by Julius Canute on 11/4/20.
//

import Foundation
import app
import RxSwift
import RxCocoa


public class ConfigurationDetailViewModel {
    let configManager: ConfigurationManager
    var state: Observable<ConfigurationDetailState> {
        stateSubject.asObservable()
    }
    private let stateSubject: BehaviorSubject<ConfigurationDetailState> = BehaviorSubject<ConfigurationDetailState>(value: .initialState)

    init(manager: ConfigurationManager) {
        self.configManager = manager
    }
}