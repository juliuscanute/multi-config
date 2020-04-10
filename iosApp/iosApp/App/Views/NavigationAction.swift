//
//  NavigationAction.swift
//  iosApp
//
//  Created by Julius Canute on 5/4/20.
//

import Foundation

public enum NavigationAction<ViewModelType>: Equatable where ViewModelType: Equatable {

  case present(view: ViewModelType)
  case presented(view: ViewModelType)
}
