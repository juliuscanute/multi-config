//
//  ConfigurationViewDelegate.swift
//  iosApp
//
//  Created by Julius Canute on 29/3/20.
//

import Foundation
import UIKit

extension ConfigurationRootView : UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        viewModel.selectNewConfiguration(selected: indexPath.row)
    }
}
