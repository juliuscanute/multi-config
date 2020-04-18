import UIKit
import app

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?
    var dependencyInjectionContainer: ConfigurationDependencyContainer!

    func initialiseMultiConfig(initializer: AppConfigurationInitializer) {
        let configurationManager = initializer.initializeAppConfiguration()
        dependencyInjectionContainer = ConfigurationDependencyContainer(configurationManager: configurationManager)
    }

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        let mainViewController = dependencyInjectionContainer.makeMainViewController()
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.makeKeyAndVisible()
        window?.rootViewController = mainViewController
        return true
    }

    func applicationWillResignActive(_ application: UIApplication) {
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
    }

    func applicationWillTerminate(_ application: UIApplication) {
    }
}

public protocol AppConfigurationInitializer {
    func initializeAppConfiguration() -> ConfigurationManager
}