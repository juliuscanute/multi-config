//
// Created by Julius Canute on 11/4/20.
//

import Foundation

protocol ConfigurationChangeResponder {
    func onConfigurationChange(state: NavigationConfigurationState)
}