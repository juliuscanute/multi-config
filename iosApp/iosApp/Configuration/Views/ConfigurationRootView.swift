//
//  ConfigurationRootView.swift
//  iosApp
//
//  Created by Julius Canute on 5/4/20.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa

class ConfigurationRootView: NiblessView {
    let configurationTableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .plain)
        tableView.register(UITableViewCell.self,
                forCellReuseIdentifier: CellIdentifier.cell.rawValue)
        tableView.tableFooterView = UIView(frame: .zero)
        return tableView
    }()
    let disposeBag = DisposeBag()
    let viewModel: ConfigurationViewModel
    var hierarchyNotReady = true

    // MARK: - Methods
    init(frame: CGRect = .zero,
         viewModel: ConfigurationViewModel) {
        self.viewModel = viewModel
        super.init(frame: frame)
        configurationTableView.dataSource = self
        configurationTableView.delegate = self

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
        configurationTableView.frame = bounds
    }

    func constructHierarchy() {
        addSubview(configurationTableView)
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

    func respond(to state: ConfigurationState) {
        let formatString = NSLocalizedString("selected_configuration", comment: "Select application configuration")
        switch state {
        case .appConfig(let configuration):
            updateViewConfiguration(formatString: formatString, configuration: configuration)
        default:
            break
        }
    }

    private func updateViewConfiguration(formatString: String, configuration: LoadApplicationConfigurationState) {
        updateSelectionIndex(configuration: configuration)
    }

    private func updateSelectionIndex(configuration: LoadApplicationConfigurationState) {
        let selectedIndexPath = IndexPath(row: configuration.selectedIndex, section: 0)
        configurationTableView.selectRow(at: selectedIndexPath, animated: true, scrollPosition: .middle)
    }
}
