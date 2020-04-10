//
//  NavigationAction.swift
//  iosApp
//
//  Created by Julius Canute on 10/4/20.
//

import Foundation

public enum NavigationAction<ViewModelType>: Equatable where MainView: Equatable {
    case present(view: ViewModelType)
    case presented(view: ViewModelType)
}

public typealias ConfigurationNavigationAction = NavigationAction<MainView>
