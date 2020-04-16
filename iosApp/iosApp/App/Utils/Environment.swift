//
//  Environment.swift
//  iosApp
//
//  Created by Julius Canute on 22/3/20.
//

import Foundation
import app

public func appConfig(apply closure: (AppConfigurationBuilder) -> Void) -> NSMutableArray {
    let builder = AppConfigurationBuilder()
    closure(builder)
    return builder.build()
}

func setup() -> NSMutableArray {
    appConfig {
        $0.config(environment: "DEV") {
            $0.switch {
                $0.key = "A"
                $0.description = "A-D"
                $0.switchValue = false
            }
        }

        $0.config(environment: "SIT") {
            $0.range {
                $0.key = "B"
                $0.description = "B-D"
                $0.min = 0
                $0.max = 1000
                $0.step = 2
                $0.currentValue = 50
            }
        }

        $0.config(environment: "UAT") {
            $0.editable {
                $0.key = "C"
                $0.description = "C-D"
                $0.currentValue = "C-V"
            }
        }

        $0.config(environment: "PROD") {
            $0.choice {
                $0.key = "D"
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


