[![Build Status](https://travis-ci.org/juliuscanute/multi-config.svg?branch=master)](https://travis-ci.org/juliuscanute/multi-config)
![Deploy to Cocoapod Action](https://github.com/juliuscanute/multi-config/workflows/Deploy%20to%20Cocoapod%20Action/badge.svg)
![MultiConfig UI](https://github.com/juliuscanute/multi-config/workflows/MultiConfig%20UI/badge.svg)
# Library
Offers a typesafe way to configure your App after deployment.

# Android

## Min API Level 21

## Dependencies

### UI
```groovy
implementation('com.juliuscanute.multiconfig:multiconfig:1.0.59@aar') {
  transitive = true
}
```

### No UI
```groovy
implementation('com.juliuscanute.multiconfignoui:multiconfignoui:1.0.59@aar') {
  transitive = true
}
```

## Configuration DSL
```kotlin
appConfig {
    config("FREE") {
        switch {
            key = "A"
            description = "Set text visibility"
            switchValue = false
        }

        range {
            key = "B"
            description = "Set text size"
            min = 16
            max = 72
            currentValue = 50
        }

        editable {
            key = "C"
            description = "Set current text"
            currentValue = "Hello Android!"
        }

        choice {
            key = "D"
            description = "Set text color"
            currentChoiceIndex = 0
            item {
                description = "RED"
            }
            item {
                description = "GREEN"
            }
            item {
                description = "BLUE"
            }
        }
    }
    config("PREMIUM") {
        switch {
            key = "A"
            description = "Set text visibility"
            switchValue = false
        }

        range {
            key = "B"
            description = "Set text size"
            min = 16
            max = 72
            currentValue = 50
        }

        editable {
            key = "C"
            description = "Set current text"
            currentValue = "Hello iOS!"
        }

        choice {
            key = "D"
            description = "Set text color"
            currentChoiceIndex = 0
            item {
                description = "RED"
            }
            item {
                description = "GREEN"
            }
            item {
                description = "BLUE"
            }
        }
    }
}
```

## Start MultiConfig

### UI
```kotlin
class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startMultiConfig(
            context = this,
            intent = Intent(this@SampleApp, MainActivity::class.java)
        ) {
            multiConfig(configuration = configuration())
        }
    }
}
```

### No UI
```kotlin
class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startMultiConfig {
            multiConfig(
                configuration = configuration()
            )
        }
    }
}
```

## Use MultiConfig
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = MultiConfigure.getConfig()
        val colorConfig = config.getConfigPair("D")
        setContent {
            MaterialTheme {
                Greeting(
                    text = config.getConfigString("C"),
                    visibility = config.getConfigBoolean("A"),
                    style = TextStyle(
                        fontSize = Sp(config.getConfigInt("B").toFloat()),
                        color = when (colorConfig.second) {
                            0 -> Color.Red
                            1 -> Color.Green
                            else -> Color.Blue
                        }
                    )
                )
            }
        }
    }
}
```

# iOS
## Dependencies
### UI & No UI
```Podfile
# Podfile
use_frameworks!

target 'YOUR_TARGET_NAME' do
    pod 'MultiConfig', '~> 1.0.59'
end
```
## Configuration DSL
```swift
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
```

## Start MultiConfig

### UI
```swift
public enum Environment {
  /*...*/
  static func initConfig() {
    multiConfig = startMultiConfig(rootGroup: "group.julius.multiconfig",
                                   controller: getLaunchController(), apply: {
                                     $0.multiConfig(configuration: configuration())
                                   })
  }
  /*...*/
}
```

### No UI
```swift
static func initConfig() {
  multiConfig = startMultiConfig(apply: {
    $0.multiConfig(configuration: configuration())
  })
```

## Use MultiConfig
```swift
final class ConfigurationData: ObservableObject {
    /*...*/
    func setData(environment: String? = nil) {
        if environment != nil {
                    Environment.multiConfig?.setEnvironment(environment: environment!)
        }
        let config: ConfigurationGetter = Environment.multiConfig!.getConfig()
        let text = config.getConfigString(userKey: "C")
        let visibility = config.getConfigBoolean(userKey: "A")
        let size = Int(config.getConfigInt(userKey: "B"))
        let currentChoice: KotlinPair = config.getConfigPair(userKey: "D")
        let choiceNo: Int = Int(currentChoice.second as! Int32)
        /*...*/
    }
}
```

## Target OS 13.0