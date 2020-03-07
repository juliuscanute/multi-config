package com.juliuscanute.multiconfig.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemDivider(context: Context, @DrawableRes resId: Int) : RecyclerView.ItemDecoration() {

    private var divider: Drawable? = ContextCompat.getDrawable(context, resId)

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        divider?.let {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            val count = parent.childCount
            for (i in 0 until count) {
                val child: View = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top: Int = child.bottom + params.bottomMargin
                val bottom = top + it.intrinsicHeight
                it.setBounds(left, top, right, bottom)
                it.draw(canvas)
            }
        }
    }
}