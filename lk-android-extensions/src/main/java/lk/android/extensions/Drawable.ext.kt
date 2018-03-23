@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package lk.android.extensions



import android.graphics.drawable.Drawable

/**
 * Various helper functions for drawables.
 * Created by jivie on 2/9/16.
 */

/**
 * Sets the bounds of the drawable to be centered around the location [centerX], [centerY] based on minimum size.
 */
inline fun Drawable.setBoundsCentered(centerX: Float, centerY: Float) = setBoundsCentered(centerX.toInt(), centerY.toInt())

/**
 * Sets the bounds of the drawable to be centered around the location [centerX], [centerY] based on minimum size.
 */
fun Drawable.setBoundsCentered(centerX: Int, centerY: Int) {
    val left = centerX - minimumWidth / 2
    val top = centerY - minimumHeight / 2
    setBounds(left, top, left + minimumWidth, top + minimumHeight)
}