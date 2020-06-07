//
//  Configuration.swift
//  sampleios
//
//  Created by Julius Canute on 16/5/20.
//  Copyright © 2020 Julius Canute. All rights reserved.
//

import Foundation
import SwiftUI
import MultiConfig
import MultiConfigCommon

public enum Environment {
    #if CONFIG
    static var isConfigApp: Bool = true
    #else
    static var isConfigApp: Bool = false
    #endif

    #if DEVELOPMENT
    static func initConfig() {
        multiConfig = startMultiConfig(rootGroup: "group.julius.multiconfig",
                controller: getLaunchController(), apply: {
            $0.multiConfig(configuration: configuration())
        })
        Environment.configData.setData()
    }

    static func configuration() -> NSMutableArray {
        appConfig {
            $0.config(environment: "FREE") {
                $0.switch {
                    $0.key = "A"
                    $0.description = "Set text visibility"
                    $0.switchValue = true
                }

                $0.range {
                    $0.key = "B"
                    $0.description = "Set text size"
                    $0.min = 16
                    $0.max = 72
                    $0.currentValue = 50
                }

                $0.editable {
                    $0.key = "C"
                    $0.description = "Set current text"
                    $0.currentValue = "Hello Android!"
                }

                $0.choice {
                    $0.key = "D"
                    $0.description = "Set text color"
                    $0.currentChoiceIndex = 0
                    $0.item {
                        $0.description = "RED"
                    }
                    $0.item {
                        $0.description = "GREEN"
                    }
                    $0.item {
                        $0.description = "BLUE"
                    }
                }
            }
            $0.config(environment: "PREMIUM") {
                $0.switch {
                    $0.key = "A"
                    $0.description = "Set text visibility"
                    $0.switchValue = false
                }

                $0.range {
                    $0.key = "B"
                    $0.description = "Set text size"
                    $0.min = 16
                    $0.max = 72
                    $0.currentValue = 50
                }

                $0.editable {
                    $0.key = "C"
                    $0.description = "Set current text"
                    $0.currentValue = "Hello iOS!"
                }

                $0.choice {
                    $0.key = "D"
                    $0.description = "Set text color"
                    $0.currentChoiceIndex = 0
                    $0.item {
                        $0.description = "RED"
                    }
                    $0.item {
                        $0.description = "GREEN"
                    }
                    $0.item {
                        $0.description = "BLUE"
                    }
                }
            }
        }
    }

    #else
    static func initConfig() {
        multiConfig = startMultiConfig(apply: {
            $0.multiConfig(configuration: Environment.configuration())
        })
        Environment.configData.setData()
    }

    static func configuration() -> NSMutableArray {
        appConfig {
            $0.config(environment: "PREMIUM") {
                $0.switch {
                    $0.key = "A"
                    $0.description = "Set text visibility"
                    $0.switchValue = true
                }

                $0.range {
                    $0.key = "B"
                    $0.description = "Set text size"
                    $0.min = 16
                    $0.max = 72
                    $0.currentValue = 50
                }

                $0.editable {
                    $0.key = "C"
                    $0.description = "Set current text"
                    $0.currentValue = "Hello Android!"
                }

                $0.choice {
                    $0.key = "D"
                    $0.description = "Set text color"
                    $0.currentChoiceIndex = 1
                    $0.item {
                        $0.description = "RED"
                    }
                    $0.item {
                        $0.description = "GREEN"
                    }
                    $0.item {
                        $0.description = "BLUE"
                    }
                }
            }
        }
    }
    #endif

    static func getLaunchController() -> MultiConfigViewController {
        return LaunchController(rootView: AnyView(ContentView().environmentObject(Environment.configData)))
    }

    static var multiConfig: MultiConfig.MultiConfigure?
    static var configData: ConfigurationData = ConfigurationData()
}
