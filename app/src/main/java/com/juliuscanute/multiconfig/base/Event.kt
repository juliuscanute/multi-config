package com.juliuscanute.multiconfig.base

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


interface Event<out T> {
    fun getContent(): T?
}

open class SingleObserverEvent<out T>(private val content: T) : Event<T> {

    private var hasBeenHandled = false
    override fun getContent(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}

open class MultiObserverEvent<out T>(private val content: T) : Event<T> {
    override fun getContent(): T? = content
}

@MainThread
inline fun <T> LiveData<SingleObserverEvent<T>>.observeSingleEvent(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<SingleObserverEvent<T>> {
    val wrappedObserver = Observer<SingleObserverEvent<T>> { t ->
        t.getContent()?.let {
            onChanged.invoke(it)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

@MainThread
inline fun <T> LiveData<MultiObserverEvent<T>>.observeMultiEvent(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<MultiObserverEvent<T>> {
    val wrappedObserver = Observer<MultiObserverEvent<T>> { t ->
        t.getContent()?.let {
            onChanged.invoke(it)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

