package builder

import model.UiControlsModel

class EditableBuilder : Builder<UiControlsModel> {
    var key: String = ""
    var description: String = ""
    var currentValue: String = ""

    override fun build(): UiControlsModel {
        check(key.isNotBlank()) { "Key must not be empty" }
        check(description.isNotBlank()) { "Description must not be empty" }
        check(currentValue.isNotBlank()) { "Current value must not be empty" }
        return UiControlsModel.Editable(
            key = key,
            description = description,
            currentValue = currentValue
        )
    }

}