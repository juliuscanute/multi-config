import UIKit
import MultiConfigCommon


public class MultiConfigInitializer {
    var dependencyInjectionContainer: ConfigurationDependencyContainer!

    public init(configurationManager: ConfigurationManager) {
        dependencyInjectionContainer = ConfigurationDependencyContainer(configurationManager: configurationManager)
    }
}

extension MultiConfigInitializer: RootViewControllerInitializer {
    public func getRootViewController() -> UINavigationController {
        let mainViewController = dependencyInjectionContainer.makeMainViewController()
        return mainViewController
    }
}

public protocol RootViewControllerInitializer {
    func getRootViewController() -> UINavigationController
}

public protocol AppConfigurationInitializer {
    func initializeAppConfiguration() -> ConfigurationManager
}
