package builder

import dsl.ConfigurationDslMarker

@ConfigurationDslMarker
interface Builder<T> {
    fun build(): T
}