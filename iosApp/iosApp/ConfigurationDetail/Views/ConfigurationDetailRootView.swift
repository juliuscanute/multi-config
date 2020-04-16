//
// Created by Julius Canute on 11/4/20.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa

enum ConfigurationDetailCell: String {
    case configurationChoiceCell
    case configurationEditableCell
    case configurationRangeCell
    case configurationSwitchCell
}

class ConfigurationDetailRootView: NiblessView {
    let configurationDetailTableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .plain)
        tableView.register(ConfigurationChoiceCell.self,
                forCellReuseIdentifier: ConfigurationDetailCell.configurationChoiceCell.rawValue)
        tableView.register(ConfigurationEditableCell.self,
                forCellReuseIdentifier: ConfigurationDetailCell.configurationEditableCell.rawValue)
        tableView.register(ConfigurationRangeCell.self,
                forCellReuseIdentifier: ConfigurationDetailCell.configurationRangeCell.rawValue)
        tableView.register(ConfigurationSwitchCell.self,
                forCellReuseIdentifier: ConfigurationDetailCell.configurationSwitchCell.rawValue)
        tableView.tableFooterView = UIView(frame: .zero)
        return tableView
    }()
    let disposeBag = DisposeBag()
    let viewModel: ConfigurationDetailViewModel
    var tableData: [ItemState]? = nil
    var hierarchyNotReady = true

    // MARK: - Methods
    init(frame: CGRect = .zero,
         viewModel: ConfigurationDetailViewModel) {
        self.viewModel = viewModel
        super.init(frame: frame)
        configurationDetailTableView.dataSource = self
        configurationDetailTableView.delegate = self
    }

    override func didMoveToWindow() {
        super.didMoveToWindow()
        guard hierarchyNotReady else {
            return
        }
        constructHierarchy()
        bindViewModelToViews()
        hierarchyNotReady = false
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        configurationDetailTableView.frame = bounds
    }

    func constructHierarchy() {
        addSubview(configurationDetailTableView)
    }

    func bindViewModelToViews() {
        viewModel
                .state
                .subscribe(onNext: { [weak self] state in
                    guard let strongSelf = self else {
                        return
                    }
                    strongSelf.respond(to: state)
                }).disposed(by: disposeBag)
    }

    func respond(to state: ConfigurationDetailState) {
        switch state {
        case .loadEnvironmentConfiguration(let configuration):
            tableData = configuration.items
            configurationDetailTableView.reloadData()
        default:
            break
        }
    }

}