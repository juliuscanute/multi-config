import UIKit
import app

class ConfigurationController: UIViewController {
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var launch: UIBarButtonItem!
    
    internal lazy var viewModel = {
        return ConfigurationViewModel(manager: { return (UIApplication.shared.delegate as? AppDelegate)!.configurationManager })
    }()
    
    var appConfig: [ConfigurationViewDataModel]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.tableFooterView = UIView(frame: .zero)
        navigationItem.title = NSLocalizedString("configuration", comment: "Application Title")
        viewModel.loadApplicationConfiguration()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        let formatString = NSLocalizedString("selected_configuration", comment: "Select application configuration")
        viewModel.state.bind { [weak self] state in
            switch state {
            case .appConfig(let configuration):
                self?.updateViewConfiguration(formatString: formatString, configuration: configuration)
            case .none:
                print("none")
            case .some(.selectedConfig(_)):
                print("none")
            case .some(.buttonConfig(_)):
                print("none")
            }
        }
    }
    
    private func updateViewConfiguration(formatString: String, configuration: LoadApplicationConfigurationState) {
        launch.title = String.localizedStringWithFormat(formatString, configuration.environment)
        guard let _ = appConfig else {
            updateData(configuration: configuration)
            updateSelectionIndex(configuration: configuration)
            return
        }
        updateSelectionIndex(configuration: configuration)
        
    }
    
    private func updateData(configuration: LoadApplicationConfigurationState){
        appConfig = configuration.items
        tableView.beginUpdates()
        let indexes = configuration.items.enumerated().map{ (index: Int, configuration: ConfigurationViewDataModel) in
            return IndexPath(row: index, section: 0)
        }
        tableView.insertRows(at: indexes, with: .automatic)
        tableView.endUpdates()
    }
    
    private func updateSelectionIndex(configuration: LoadApplicationConfigurationState){
        let selectedIndexPath = IndexPath(row: configuration.selectedIndex, section: 0)
        tableView.selectRow(at: selectedIndexPath, animated: true, scrollPosition: .middle)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
