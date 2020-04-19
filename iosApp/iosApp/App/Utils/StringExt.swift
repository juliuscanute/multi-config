//
// Created by Julius Canute on 18/4/20.
// Copyright (c) 2020 Julius Canute. All rights reserved.
//

import Foundation

typealias StringDefinition = (key: String, comment: String)

class AppString {
    private static let _configuration: StringDefinition = (key: "configuration", comment: "Application Title")
    private static let _selected_configuration: StringDefinition = (key: "selected_configuration", comment: "Select application configuration")
    private static let _switch_on = (key: "switch_on", comment: "On state of switch")
    private static let _cancel = (key: "cancel", comment: "Dont save changes")
    private static let _switch_off = (key: "switch_off", comment: "Off state of switch")
    private static let _edit_configuration = (key: "edit_configuration", comment: "Edit the configuration")
    private static let _choose_configuration = (key: "choose_configuration", comment: "Choose a configuration")
    private static let _ok = (key: "ok", comment: "Save changes")

    static var configuration: String {
        get {
            getString(definitionFor: _configuration)
        }
    }
    static var selected_configuration: String {
        get {
            getString(definitionFor: _selected_configuration)
        }
    }
    static var switch_on: String {
        get {
            getString(definitionFor: _switch_on)
        }
    }
    static var switch_off: String {
        get {
            getString(definitionFor: _switch_off)
        }
    }
    static var edit_configuration: String {
        get {
            getString(definitionFor: _edit_configuration)
        }
    }
    static var choose_configuration: String {
        get {
            getString(definitionFor: _choose_configuration)
        }
    }
    static var ok: String {
        get {
            getString(definitionFor: _ok)
        }
    }
    static var cancel: String {
        get {
            getString(definitionFor: _cancel)
        }
    }

    private static func getString(definitionFor: StringDefinition) -> String {
        NSLocalizedString(definitionFor.key, bundle: Bundle(for: AppString.self), comment: definitionFor.comment)
    }
}
