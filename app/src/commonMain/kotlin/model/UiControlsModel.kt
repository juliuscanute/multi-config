package model

sealed class UiControlsModel {

    data class Switch(val key: String, val information: String, var switchValue: Boolean) :
        UiControlsModel()

    data class Range(
        val key: String,
        val information: String,
        val min: Int,
        val max: Int,
        var currentValue: Int
    ) : UiControlsModel()

    data class Editable(
        val key: String,
        val information: String,
        var currentValue: String
    ) : UiControlsModel()

    data class Choice(
        val key: String,
        val information: String,
        val items: ArrayList<Item>,
        var currentChoiceIndex: Int
    ) : UiControlsModel()

}

data class Item(val information: String)