//
//  ConfigurationViewDataSource.swift
//  iosApp
//
//  Created by Julius Canute on 29/3/20.
//

import Foundation
import UIKit

extension ConfigurationController : UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return appConfig?.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ListItemConfigurationCell", for: indexPath) as! ListItemConfigurationCell
        let config = appConfig?[indexPath.row]
        cell.label.text = config?.environment
        return cell
    }
}
