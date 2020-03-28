import UIKit
import app

class ConfigurationController: UIViewController {
    @IBOutlet weak var tableView: UITableView!
    
    lazy var manager: ConfigurationManager = {
        return (UIApplication.shared.delegate as? AppDelegate)!.configurationManager
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.tableFooterView = UIView(frame: .zero)
        navigationItem.title = "Configuration"
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

extension ConfigurationController : UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let appConfig = manager.getApplicationConfiguration()
        return appConfig.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ListItemConfigurationCell", for: indexPath) as! ListItemConfigurationCell
        let appConfig = manager.getApplicationConfiguration()
        let config = appConfig[indexPath.row] as! Configuration
        cell.label.text = config.environment
        return cell
    }
}
