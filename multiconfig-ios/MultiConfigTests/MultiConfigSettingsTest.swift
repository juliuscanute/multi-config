//
//  MultiConfigSettingsTest.swift
//  MultiConfigTests
//
//  Created by Julius Canute on 14/5/20.
//  Copyright © 2020 Julius Canute. All rights reserved.
//

import XCTest
@testable import MultiConfig
@testable import MultiConfigCommon

class MultiConfigSettingsTest: XCTestCase {

    var config: NSMutableArray!
    var controller: ApplicationLaunchController!

    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
        super.setUp()
        controller = LaunchStubController()
        config = appConfig {
            $0.config(environment: "DEV") {
                $0.switch {
                    $0.key = "AA"
                    $0.description = "A-D"
                    $0.switchValue = true
                }
                $0.range {
                    $0.key = "AB"
                    $0.description = "B-D"
                    $0.min = 0
                    $0.max = 1000
                    $0.step = 2
                    $0.currentValue = 50
                }
            }

            $0.config(environment: "SIT") {
                $0.range {
                    $0.key = "AB"
                    $0.description = "B-D"
                    $0.min = 0
                    $0.max = 1000
                    $0.step = 2
                    $0.currentValue = 50
                }
            }

            $0.config(environment: "UAT") {
                $0.editable {
                    $0.key = "BC"
                    $0.description = "C-D"
                    $0.currentValue = "C-V"
                }
            }

            $0.config(environment: "PROD") {
                $0.choice {
                    $0.key = "CD"
                    $0.description = "D-D"
                    $0.currentChoiceIndex = 0
                    $0.item {
                        $0.description = "E"
                    }
                    $0.item {
                        $0.description = "F"
                    }
                    $0.item {
                        $0.description = "G"
                    }
                }
            }
        }
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        controller = nil
        config = nil
        super.tearDown()
    }


    func testWithLaunchController() throws {
        startMultiConfig(rootGroup: "group.fake") {
            $0.multiConfig(configuration: self.config, controller: self.controller)
        }
        let configuration = MultiOSConfig.getConfig()
        let value = configuration.getConfigBoolean(userKey: "AA")
        XCTAssertEqual(value, true, "value must match config")
        XCTAssert(configuration is ConfigurationRepository)
    }
    
    class LaunchStubController : ApplicationLaunchController {
        func launchController(environment: String) -> UIViewController {
            XCTAssertEqual(environment, "DEV","environment must match primary environment")
            return UIViewController()
        }
    }

}
