package com.juliuscanute.multiconfig.builder

import com.juliuscanute.multiconfig.model.Item

class ItemBuilder : Builder<Item> {
    var description: String = ""

    override fun build(): Item {
        check(description.isNotBlank()) { "Description must not be empty" }
        return Item(information = description)
    }
}