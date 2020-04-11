import UIKit
import RxSwift
import RxCocoa
import app

public class ConfigurationController: NiblessViewController {
    let disposeBag = DisposeBag()
    var appConfig: [ConfigurationViewDataModel]?
    let viewModel: ConfigurationViewModel
    let launchButton: UIButton = {
        let button = UIButton(type: .system)
        return button
    }()

    init(configurationViewModelFactory: ConfigurationViewModelFactory) {
        self.viewModel = configurationViewModelFactory.makeConfigurationViewModel()
        super.init()
    }

    public override func loadView() {
        self.view = ConfigurationRootView(viewModel: viewModel)
        self.toolbarItems = [UIBarButtonItem(customView: buildToolbarView())]
    }

    override public func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.title = NSLocalizedString("configuration", comment: "Application Title")
        viewModel.loadApplicationConfiguration()
    }

    private func buildToolbarView() -> UIStackView {
        let stackView = UIStackView(frame: self.navigationController?.toolbar.frame ?? .zero)
        stackView.distribution = .fill
        stackView.alignment = .top
        stackView.axis = .horizontal
        stackView.addArrangedSubview(launchButton)
        stackView.spacing = 8.0
        return stackView
    }

    func bindViewModel() {
        let formatString = NSLocalizedString("selected_configuration", comment: "Select application configuration")
        viewModel
                .launchText
                .distinctUntilChanged()
                .subscribe(onNext: { [weak self] launchText in
                    guard let strongSelf = self else {
                        return
                    }
                    strongSelf.launchButton.setTitle(String.localizedStringWithFormat(formatString, launchText), for: .normal)
                }).disposed(by: disposeBag)
    }
}

protocol ConfigurationViewModelFactory {

    func makeConfigurationViewModel() -> ConfigurationViewModel
}
