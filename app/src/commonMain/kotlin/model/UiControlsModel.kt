package model

sealed class UiControlsModel {

    data class Switch(val key: String, val description: String, var switchValue: Boolean) :
        UiControlsModel()

    data class Range(
        val key: String,
        val description: String,
        val min: Int,
        val max: Int,
        var currentValue: Int
    ) : UiControlsModel()

    data class Editable(
        val key: String,
        val description: String,
        var currentValue: String
    ) : UiControlsModel()

    data class Choice(
        val key: String,
        val description: String,
        val items: ArrayList<Item>,
        var currentChoiceIndex: Int
    ) : UiControlsModel()

}

data class Item(val description: String)