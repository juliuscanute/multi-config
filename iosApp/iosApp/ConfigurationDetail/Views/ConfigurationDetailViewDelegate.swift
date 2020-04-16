//
// Created by Julius Canute on 11/4/20.
//

import UIKit

extension ConfigurationDetailRootView: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let config = tableData![indexPath.row]
        tableView.deselectRow(at: indexPath, animated: true)
        switch config {
        case .editableState(let state):
            print("none")
        case .choiceState(let state):
            viewModel.showAvailableChoices(choiceState: state)
        default:
            break
        }
    }
}