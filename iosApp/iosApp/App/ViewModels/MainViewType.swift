//
//  MainView.swift
//  iosApp
//
//  Created by Julius Canute on 4/4/20.
//

import Foundation

public enum MainViewType: Equatable {
    case configuration
    case configurationDetail(environment: String)

    public func hidesNavigationBar() -> Bool {
        switch self {
        default:
            return false
        }
    }

    public func hidesToolBar() -> Bool {
        switch self {
        default:
            return false
        }
    }

}
