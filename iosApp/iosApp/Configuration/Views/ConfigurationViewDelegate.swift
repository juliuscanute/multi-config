//
//  ConfigurationViewDelegate.swift
//  iosApp
//
//  Created by Julius Canute on 29/3/20.
//

import Foundation
import UIKit
import MultiConfigCommon

extension ConfigurationRootView: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        viewModel.selectNewConfiguration(selected: indexPath.row)
    }

    func tableView(_ tableView: UITableView, accessoryButtonTappedForRowWith indexPath: IndexPath) {
        let config = viewModel.getConfigurationForIndex(index: indexPath.row)
        viewModel.moveToConfigurationDetail(environment: config.environment)
    }
}
