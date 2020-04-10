import UIKit
import app

class ConfigurationController:  NiblessViewController{
    var appConfig: [ConfigurationViewDataModel]?
    let viewModel: ConfigurationViewModel
    init(configurationViewModelFactory: ConfigurationViewModelFactory) {
        self.viewModel = configurationViewModelFactory.makeConfigurationViewModel()
        super.init()
    }
    
    public override func loadView() {
        self.view = ConfigurationRootView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.title = NSLocalizedString("configuration", comment: "Application Title")
        viewModel.loadApplicationConfiguration()
    }
}

protocol ConfigurationViewModelFactory {
    
    func makeConfigurationViewModel() -> ConfigurationViewModel
}
