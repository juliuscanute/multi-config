//
// Created by Julius Canute on 12/4/20.
//

import Foundation

enum ItemState {
    case switchState(SwitchState)
    case rangeState(RangeState)
    case editableState(EditableState)
    case choiceState(ChoiceState)
}

struct SwitchState {
    let key: String
    let description: String
    let switchValue: Bool

    func getSwitchStatus() -> String {
        if (switchValue) {
            return AppString.switch_on
        } else {
            return AppString.switch_off
        }
    }
}

struct RangeState {
    let key: String
    let description: String
    let min: Int
    let max: Int
    let currentValue: Int

    func getMinString() -> String {
        String(min)
    }

    func getMaxString() -> String {
        String(max)
    }

    func getValueString() -> String {
        String(currentValue)
    }
}

public struct EditableState {
    let key: String
    let description: String
    let currentValue: String
}

public struct ChoiceState {
    let key: String
    let description: String
    let currentChoiceIndex: Int
    let items: [ChoiceItem]

    func getSelectedItem() -> String {
        items[currentChoiceIndex].description
    }
}

struct ChoiceItem {
    let description: String
}