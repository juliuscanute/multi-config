import UIKit
import RxSwift
import RxCocoa
import MultiConfigCommon

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

    public override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        viewModel.loadApplicationConfiguration()
    }
}

protocol ConfigurationViewModelFactory {

    func makeConfigurationViewModel() -> ConfigurationViewModel
}
