//
//  MainView.swift
//  iosApp
//
//  Created by Julius Canute on 10/4/20.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa

class ToolbarView: NiblessBarButtonItem {
    let launchButton: UIButton = {
        let button = UIButton()
        return button
    }()
    
    let toolbarStack: UIStatckView = {
        let stack = UIStatckView(arrangedSubviews: [launchButton])
        stack.distribution = .fill
        stack.alignment = .top
        stack.axis = .horizontal
        return stack
    }()
    
    let viewModel: MainViewModel
    
    var hierarchyNotReady = true
    
    init(frame: CGRect = .zero,
         viewModel: MainViewModel) {
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
    
    func constructHierarchy() {
        self.customView = toolbarStack
    }
    
    func bindViewModel() {
        viewModel
            .launchText
            .distinctUntilChanged()
            .subscribe(onNext: { [weak self] launchText in
                guard let strongSelf = self else { return }
                strongSelf.launchButton.text = String.localizedStringWithFormat(formatString, configuration.environment)
            }).disposed(by: disposeBag)
    }
}
