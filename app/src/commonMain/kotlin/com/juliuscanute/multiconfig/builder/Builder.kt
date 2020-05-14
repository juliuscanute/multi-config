package com.juliuscanute.multiconfig.builder

import com.juliuscanute.multiconfig.dsl.ConfigurationDslMarker

@ConfigurationDslMarker
interface Builder<T> {
    fun build(): T
}