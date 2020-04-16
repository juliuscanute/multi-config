//
// Created by Julius Canute on 11/4/20.
//

import Foundation
import UIKit

extension ConfigurationDetailRootView: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        tableData?.count ?? 0
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let config = tableData![indexPath.row]
        switch config {
        case .switchState(let state):
            let cell = tableView.dequeueReusableCell(withIdentifier:
            ConfigurationDetailCell.configurationSwitchCell.rawValue, for: indexPath) as! ConfigurationSwitchCell
            cell.delegate = viewModel
            cell.selectionStyle = .none
            cell.switchState = state
            return cell
        case .rangeState(let state):
            let cell = tableView.dequeueReusableCell(withIdentifier:
            ConfigurationDetailCell.configurationRangeCell.rawValue, for: indexPath) as! ConfigurationRangeCell
            cell.delegate = viewModel
            cell.selectionStyle = .none
            cell.rangeState = state
            return cell
        case .editableState(let state):
            let cell = tableView.dequeueReusableCell(withIdentifier:
            ConfigurationDetailCell.configurationEditableCell.rawValue, for: indexPath) as! ConfigurationEditableCell
            cell.editableState = state
            return cell
        case .choiceState(let state):
            let cell = tableView.dequeueReusableCell(withIdentifier:
            ConfigurationDetailCell.configurationChoiceCell.rawValue, for: indexPath) as! ConfigurationChoiceCell
            cell.choiceState = state
            return cell
        }
    }
}