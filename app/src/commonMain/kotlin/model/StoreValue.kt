package model

sealed class StoreValue {
    data class BooleanValue(val value: Boolean) : StoreValue()
    data class IntValue(val value: Int) : StoreValue()
    data class StringValue(val value: String) : StoreValue()
    data class KeyValue(val key: String, val value: Int) : StoreValue()
}