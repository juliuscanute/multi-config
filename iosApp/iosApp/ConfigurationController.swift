import UIKit
import app

class ConfigurationController: UIViewController {
    
    lazy var manager: ConfigurationManager = {
        return (UIApplication.shared.delegate as? AppDelegate)!.configurationManager
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
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
        let cell = tableView.dequeueReusableCell(withIdentifier: "MultiConfigConfigurationCell", for: indexPath)
        let appConfig = manager.getApplicationConfiguration()
        let config = appConfig[indexPath.row] as! Configuration
        cell.textLabel?.text = config.environment
        return cell
    }
}
