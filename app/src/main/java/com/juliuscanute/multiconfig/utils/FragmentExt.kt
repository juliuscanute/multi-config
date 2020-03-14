@file:Suppress("UNCHECKED_CAST")

package com.juliuscanute.multiconfig.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> Fragment.buildViewModel(crossinline viewModelFactory: () -> T): T {
    return ViewModelProvider(this, object : ViewModelProvider.NewInstanceFactory() {
        override fun <A : ViewModel?> create(modelClass: Class<A>): A {
            return viewModelFactory() as A
        }
    }).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.buildViewModel(
    context: FragmentActivity,
    crossinline viewModelFactory: () -> T
): T {
    return ViewModelProvider(context, object : ViewModelProvider.NewInstanceFactory() {
        override fun <A : ViewModel?> create(modelClass: Class<A>): A {
            return viewModelFactory() as A
        }
    }).get(T::class.java)
}