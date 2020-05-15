//
//  ConfigurationViewDataSource.swift
//  iosApp
//
//  Created by Julius Canute on 29/3/20.
//

import Foundation
import UIKit

enum ConfigurationEnvironmentIdentifier: String {
    case cell
}

extension ConfigurationRootView: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        viewModel.getAvailableConfigurationCount()
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: ConfigurationEnvironmentIdentifier.cell.rawValue, for: indexPath)
        let config = viewModel.getConfigurationForIndex(index: indexPath.row)
        cell.textLabel?.text = config.environment
        cell.accessoryType = UITableViewCell.AccessoryType.detailButton
        return cell
    }
}
