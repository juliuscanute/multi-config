package com.juliuscanute.multiconfig.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> Fragment.buildViewModel(crossinline viewModelFactory: () -> T): T {
    return ViewModelProviders.of(this, object : ViewModelProvider.NewInstanceFactory() {
        override fun <A : ViewModel?> create(modelClass: Class<A>): A {
            return viewModelFactory() as A
        }
    }).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.buildViewModel(
    context: FragmentActivity,
    crossinline viewModelFactory: () -> T
): T {
    return ViewModelProviders.of(context, object : ViewModelProvider.NewInstanceFactory() {
        override fun <A : ViewModel?> create(modelClass: Class<A>): A {
            return viewModelFactory() as A
        }
    }).get(T::class.java)
}