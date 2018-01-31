@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Adds an [RecyclerView.ItemDecoration] that draws a horizontal divider between items.
 */
fun RecyclerView.horizontalDivider(drawable: Drawable, dividerSize: Int = drawable.intrinsicHeight.coerceAtLeast(1), padding: Int = 0) {
    addItemDecoration(object : RecyclerView.ItemDecoration() {

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val left = parent.paddingLeft + padding
            val right = parent.width - parent.paddingRight - padding

            val childCount = parent.childCount
            for (i in 0..childCount - 1) {
                val child = parent.getChildAt(i)

                drawable.alpha = (child.alpha * 255).toInt()

                val top = child.let {
                    top - (
                            (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin ?: 0
                            ) + (translationY + .5f).toInt()
                } - dividerSize

                if (i != 0) {
                    drawable.setBounds(left, top, right, top + dividerSize)
                    drawable.draw(c)
                }

                val bottom = child.let {
                    bottom + (
                            (layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin ?: 0
                            ) + (translationY + .5f).toInt()
                }

                if (i != parent.childCount - 1) {
                    drawable.setBounds(left, bottom, right, bottom + dividerSize)
                    drawable.draw(c)
                }
            }
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.set(0, 0, 0, dividerSize)
        }
    })
}

/**
 * Adds an [RecyclerView.ItemDecoration] that draws a vertical divider between items.
 */
fun RecyclerView.verticalDivider(drawable: Drawable, dividerSize: Int = drawable.intrinsicHeight.coerceAtLeast(1), padding: Int = 0) {
    addItemDecoration(object : RecyclerView.ItemDecoration() {

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val top = parent.paddingTop + padding
            val bottom = parent.height - parent.paddingBottom - padding

            val childCount = parent.childCount
            for (i in 0..childCount - 1) {
                val child = parent.getChildAt(i)

                drawable.alpha = (child.alpha * 255).toInt()

                val left = child.let {
                    left - (
                            (layoutParams as? ViewGroup.MarginLayoutParams)?.leftMargin ?: 0
                            ) + (translationX + .5f).toInt()
                } - dividerSize

                if (i != 0) {
                    drawable.setBounds(left, top, right, top + dividerSize)
                    drawable.draw(c)
                }

                val right = child.let {
                    right + (
                            (layoutParams as? ViewGroup.MarginLayoutParams)?.rightMargin ?: 0
                            ) + (translationX + .5f).toInt()
                }

                if (i != parent.childCount - 1) {
                    drawable.setBounds(right, top, right + dividerSize, bottom)
                    drawable.draw(c)
                }
            }
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.set(0, 0, 0, dividerSize)
        }
    })
}