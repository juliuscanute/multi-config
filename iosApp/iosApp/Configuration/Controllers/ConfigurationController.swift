import UIKit
import RxSwift
import RxCocoa
import app

public class ConfigurationController: NiblessViewController {
    let disposeBag = DisposeBag()
    let viewModel: ConfigurationViewModel

    init(baseViewModel: BaseViewModel, configurationViewModelFactory: ConfigurationDependencyContainer) {
        self.viewModel = configurationViewModelFactory.makeConfigurationViewModel()
        super.init(viewModel: baseViewModel)
    }

    public override func loadView() {
        self.view = ConfigurationRootView(viewModel: viewModel)
        attachView()
    }

    override public func viewDidLoad() {
        super.viewDidLoad()
        viewModel.loadApplicationConfiguration()
    }
}

protocol ConfigurationViewModelFactory {

    func makeConfigurationViewModel() -> ConfigurationViewModel
}
