//
//  MainViewController.swift
//  iosApp
//
//  Created by Julius Canute on 4/4/20.
//

import Foundation
import UIKit

class MainViewController : NiblessNavigationController {
    let viewModel: MainViewModel
    let configurationController: ConfigurationController
    
    public init(viewModel: MainViewModel,
                configurationController: ConfigurationController){
        self.viewModel = viewModel
        self.configurationController = configurationController
        super.init()
        self.delegate = self
    }
    
    public override func viewDidLoad() {
        super.viewDidLoad()
        subscribe(to: viewModel.view)
    }
    
    func subscribe(to observable: Observable<ConfigurationNavigationAction>) {
        observable
            .distinctUntilChanged()
            .subscribe(onNext: { [weak self] action in
                guard let strongSelf = self else { return }
                strongSelf.respond(to: action)
            }).disposed(by: disposeBag)
    }
    
    func respond(to navigationAction: ConfigurationNavigationAction) {
        switch navigationAction {
        case .present(let view):
            present(view: view)
        case .presented:
            break
        }
    }
    
    func present(view: MainVIewType) {
        switch view {
        case .configuration:
            presentConfiguration()
        }
    }
    
    func presentConfiguration() {
        pushViewController(configurationController, animated: false)
    }
}

extension OnboardingViewController {
    
    func hideOrShowNavigationBarIfNeeded(for view: MainViewType, animated: Bool) {
        if view.hidesNavigationBar() {
            hideNavigationBar(animated: animated)
        } else {
            showNavigationBar(animated: animated)
        }
        
        if view.hidesToolBar() {
            hideToolBar()
        } else {
            showToolBar()
        }
    }
    func hideToolBar() {
        if animated {
            transitionCoordinator?.animate(alongsideTransition: { context in
                self.setToolbarHidden(true, animated: animated)
            })
        } else {
            self.setToolbarHidden(true, animated: false)
        }
    }
    
    func showToolBar() {
        if self.isToolbarHidden {
            self.setToolbarHidden(false, animated: false)
            self.toolbarItems = [ToolbarView(frame: self.frame)]
        }
    }
    
    func hideNavigationBar(animated: Bool) {
        if animated {
            transitionCoordinator?.animate(alongsideTransition: { context in
                self.setNavigationBarHidden(true, animated: animated)
            })
        } else {
            setNavigationBarHidden(true, animated: false)
        }
    }
    
    func showNavigationBar(animated: Bool) {
        if self.isNavigationBarHidden {
            self.setNavigationBarHidden(false, animated: animated)
        }
    }
}

extension MainViewController: UINavigationControllerDelegate {
    
    public func navigationController(_ navigationController: UINavigationController,
                                     willShow viewController: UIViewController,
                                     animated: Bool) {
        guard let viewToBeShown = mainView(associatedWith: viewController) else { return }
        hideOrShowNavigationBarIfNeeded(for: viewToBeShown, animated: animated)
    }
    
    public func navigationController(_ navigationController: UINavigationController,
                                     didShow viewController: UIViewController,
                                     animated: Bool) {
        guard let shownView = mainView(associatedWith: viewController) else { return }
        viewModel.uiPresented(mainView: shownView)
    }
}

extension MainViewController {
    
    func mainView(associatedWith viewController: UIViewController) -> MainViewType? {
        switch viewController {
        case is ConfigurationController:
            return .configuration
        default:
            assertionFailure("Encountered unexpected child view controller type in MainViewController")
            return nil
        }
    }
}

