//
//  NiblessViewController.swift
//  iosApp
//
//  Created by Julius Canute on 4/4/20.
//

import UIKit
import RxSwift
import RxCocoa


open class NiblessViewController: UIViewController {
    private let disposeBag = DisposeBag()
    private let viewModel: BaseViewModel
    private let launchButton: UIButton = {
        let button = UIButton(type: .system)
        return button
    }()

    private let stackView: UIStackView = {
        let stackView: UIStackView = UIStackView()
        stackView.distribution = .fill
        stackView.alignment = .top
        stackView.axis = .horizontal
        stackView.spacing = 8.0
        return stackView
    }()

    // MARK: - Methods
    public init(viewModel: BaseViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable,
               message: "Loading this view controller from a nib is unsupported in favor of initializer dependency injection."
    )
    public required init?(coder aDecoder: NSCoder) {
        fatalError("Loading this view controller from a nib is unsupported in favor of initializer dependency injection.")
    }

    func attachView() {
        stackView.frame = self.navigationController?.toolbar.frame ?? .zero
        stackView.addArrangedSubview(launchButton)
        self.toolbarItems = [UIBarButtonItem(customView: stackView)]
        bindViewModelToViews()
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

    func respond(to state: ApplicationState) {
        switch state {
        case .navigationControlConfig(let buttonState):
            applyAppTitle(state: buttonState)
            applyBackTitle(state: buttonState)
            applyButtonText(state: buttonState)
        default:
            break
        }
    }

    func applyAppTitle(state: NavigationConfigurationState) {
        navigationItem.title = state.title
    }

    func applyBackTitle(state: NavigationConfigurationState) {
        guard let backTitle = state.backTitle else {
            return
        }
        let button = UIBarButtonItem()
        button.title = backTitle
        navigationItem.backBarButtonItem = button
    }

    func applyButtonText(state: NavigationConfigurationState) {
        guard let buttonText = state.buttonTitle, let environment = state.environment else {
            return
        }
        launchButton.setTitle(String.localizedStringWithFormat(buttonText, environment), for: .normal)
    }
}
