package builder

import model.UiControlsModel

class RangeBuilder : Builder<UiControlsModel> {
    var key: String = ""
    var description: String = ""
    var min: Int = 0
    var max: Int = 0
    var step: Int = 0
    var currentValue: Int = 0

    override fun build(): UiControlsModel {
        check(key.isNotBlank()) { "Key must not be empty" }
        check(description.isNotBlank()) { "Description must not be empty" }
        check(max > min) { "Max should be greater than Min" }
        check(step != 0) { "Step must not be zero" }
        check(currentValue in min..max) { "Current value must be between Max & Min" }
        return UiControlsModel.Range(
            key = key,
            description = description,
            min = min,
            max = max,
            step = step,
            currentValue = currentValue
        )
    }

}