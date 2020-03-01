package builder

import model.UiControlsModel

class SwitchBuilder : Builder<UiControlsModel> {
    var key: String = ""
    var description: String = ""
    var switchValue: Boolean = false

    override fun build(): UiControlsModel {
        check(key.isNotBlank()) { "Key must not be empty" }
        check(description.isNotBlank()) { "Description must not be empty" }
        return UiControlsModel.Switch(
            key = key,
            description = description,
            switchValue = switchValue
        )
    }
}