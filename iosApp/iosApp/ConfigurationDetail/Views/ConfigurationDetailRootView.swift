//
// Created by Julius Canute on 11/4/20.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa

class ConfigurationDetailRootVIew: NiblessView {
    let configurationTableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .plain)
        tableView.register(UITableViewCell.self,
                forCellReuseIdentifier: CellIdentifier.cell.rawValue)
        tableView.tableFooterView = UIView(frame: .zero)
        return tableView
    }()
    let disposeBag = DisposeBag()
    let viewModel: ConfigurationDetailViewModel
    var hierarchyNotReady = true

    // MARK: - Methods
    init(frame: CGRect = .zero,
         viewModel: ConfigurationDetailViewModel) {
        self.viewModel = viewModel
        super.init(frame: frame)
    }

    override func didMoveToWindow() {
        super.didMoveToWindow()
        guard hierarchyNotReady else {
            return
        }
        constructHierarchy()
        hierarchyNotReady = false
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        configurationTableView.frame = bounds
    }

    func constructHierarchy() {
        addSubview(configurationTableView)
    }
}