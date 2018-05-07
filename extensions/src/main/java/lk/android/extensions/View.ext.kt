@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package lk.android.extensions



import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Convenience function for `ViewCompat.isAttachedToWindow(this)`.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun View.isAttachedToWindowCompat(): Boolean = ViewCompat.isAttachedToWindow(this)

/**
 * Allows you to modify the elevation on a view without worrying about version.
 */
var View.elevationCompat: Float
    get() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return elevation
        }
        return 0f
    }
    set(value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = value
        }
    }


/**
 * Attempts to get the activity of the view.
 */
fun View.getActivity(): Activity? {
    return context.getActivity()
}

/**
 * Shows the soft input for the vindow.
 */
fun View.showSoftInput() {
    context.getSystemService(Context.INPUT_METHOD_SERVICE).let { it as InputMethodManager }.showSoftInput(this, 0)
}

/**
 * Hides the soft input for the vindow.
 */
fun View.hideSoftInput() {
    context.getSystemService(Context.INPUT_METHOD_SERVICE).let { it as InputMethodManager }.hideSoftInputFromWindow(this.applicationWindowToken, 0)
}

/**
 * Measures the desired size of the view.
 */
fun View.measureDesiredSize(maxWidth: Int = Int.MAX_VALUE / 2 - 1, maxHeight: Int = Int.MAX_VALUE / 2 - 1): Point {
    this.measure(
            View.MeasureSpec.makeMeasureSpec(maxWidth, View.MeasureSpec.AT_MOST),
            View.MeasureSpec.makeMeasureSpec(maxHeight, View.MeasureSpec.AT_MOST)
    )
    return Point(measuredWidth, measuredHeight)
}

/**
 * Checks if the view is in layout, returns false if SDK version isn't high enough.
 */
fun View.isInLayoutCompat(): Boolean {
    return if (Build.VERSION.SDK_INT >= 18)
        isInLayout
    else
        false
}

/**
 * Requests a layout in a more safe manner by checking if we are currently in a layout pass.
 */
fun View.requestLayoutSafe() {
    if (Build.VERSION.SDK_INT >= 18) {
        if (!isInLayout) {
            requestLayout()
        }
    } else {
        requestLayout()
    }
}

/**
 * Sets an on click listener for a view, but ensures the action cannot be triggered more often than [cooldown] milliseconds.
 */
@Deprecated("Use setOnClickListenerCooldown instead.", ReplaceWith("setOnClickListenerCooldown", "lk.android.extensions.setOnClickListenerCooldown"))
inline fun View.onClickWithCooldown(cooldown: Long = 1000L, crossinline action: () -> Unit) = setOnClickListenerCooldown(cooldown, action)

/**
 * Sets an on click listener for a view, but ensures the action cannot be triggered more often than [cooldown] milliseconds.
 */
inline fun View.setOnClickListenerCooldown(cooldown: Long = 1000L, crossinline action: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        var lastTime = 0L
        override fun onClick(v: View?) {
            val now = System.currentTimeMillis()
            if (now - lastTime > cooldown) {
                action()
                lastTime = now
            }
        }
    })
}