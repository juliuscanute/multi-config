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
        navigationItem.title = NSLocalizedString("configuration", comment: "Application Title")
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
        let formatString = NSLocalizedString("selected_configuration", comment: "Select application configuration")
        switch state {
        case .buttonConfig(let buttonState):
            launchButton.setTitle(String.localizedStringWithFormat(formatString, buttonState.environment), for: .normal)
        default:
            break
        }
    }
}
