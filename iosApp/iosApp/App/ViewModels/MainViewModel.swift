//
//  MainViewModel.swift
//  iosApp
//
//  Created by Julius Canute on 4/4/20.
//

import Foundation
import RxSwift

public class MainViewModel : ConfigurationDetailResponder {
    // MARK: - Properties
    public var view: Observable<ConfigurationNavigationAction> { return viewSubject.asObservable() }
    private let viewSubject = BehaviorSubject<ConfigurationNavigationAction>(value: .present(view: .configuration))
    public var launchText: Observable<String> { return viewSubject.asObservable() }
    private let launchTextSubject:BehaviorSubject<String> = BehaviorSubject<String>()
    
    public func selectConfiguration(environment: String) {
        launchTextSubject.onNext(environment)
    }
    
    public func uiPresented(mainView: MainViewType) {
        viewSubject.onNext(.presented(view: mainView))
    }
}
