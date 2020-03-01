package builder

import model.Item

class ItemBuilder : Builder<Item> {
    var description: String = ""

    override fun build(): Item {
        check(description.isNotBlank()) { "Description must not be empty" }
        return Item(description = description)
    }
}