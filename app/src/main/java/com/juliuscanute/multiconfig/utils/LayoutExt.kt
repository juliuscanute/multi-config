package com.juliuscanute.multiconfig.utils

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.juliuscanute.multiconfig.R

@BindingAdapter("itemSelectedState")
fun View.setItemSelectedState(selected: Boolean) {
    if (selected) {
        setBackgroundColor(ContextCompat.getColor(context, context.getThemeColorId(R.attr.colorAccent)))
    } else {
        setBackgroundColor(ContextCompat.getColor(context, context.getThemeColorId(R.attr.colorSurface)))
    }
}