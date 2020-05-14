package com.juliuscanute.multiconfig.builder

import com.juliuscanute.multiconfig.model.Item
import com.juliuscanute.multiconfig.model.UiControlsModel

class ChoiceBuilder : Builder<UiControlsModel> {
    var key: String = ""
    var description: String = ""
    var currentChoiceIndex: Int = 0
    private val items: MutableList<Item> = mutableListOf()

    fun item(itemBuilder: ItemBuilder.() -> Unit) {
        items.add(ItemBuilder().apply(itemBuilder).build())
    }

    override fun build(): UiControlsModel {
        check(key.isNotBlank()) { "Key must not be empty" }
        check(description.isNotBlank()) { "Description must not be empty" }
        check(items.isNotEmpty()) { "Items must not be empty" }
        check(items.size > 1) { "Number of items in choice must be greater than one" }
        return UiControlsModel.Choice(
            key = key,
            information = description,
            items = ArrayList(items),
            currentChoiceIndex = currentChoiceIndex
        )
    }
}