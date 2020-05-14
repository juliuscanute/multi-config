package com.juliuscanute.multiconfig.builder

import com.juliuscanute.multiconfig.model.UiControlsModel

class SwitchBuilder : Builder<UiControlsModel> {
    var key: String = ""
    var description: String = ""
    var switchValue: Boolean = false

    override fun build(): UiControlsModel {
        check(key.isNotBlank()) { "Key must not be empty" }
        check(description.isNotBlank()) { "Description must not be empty" }
        return UiControlsModel.Switch(
            key = key,
            information = description,
            switchValue = switchValue
        )
    }
}