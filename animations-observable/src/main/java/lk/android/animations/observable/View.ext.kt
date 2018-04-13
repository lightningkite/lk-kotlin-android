@file:JvmName("LkAndroidAnimationsObservable")
@file:JvmMultifileClass

package lk.android.animations.observable

import android.os.Build
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import lk.android.animations.TypedValueAnimator
import lk.android.animations.heightAnimator
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.lifecycle.listen

/**
 * Makes the view expand to full size when the observable is true, and contract otherwise.
 * Animates.
 */
fun <T : View> T.expanding(expandedObs: ObservableProperty<Boolean>): T {
    var animator: TypedValueAnimator.IntAnimator? = null
    lifecycle.listen(expandedObs) {
        val final = if (it) WRAP_CONTENT else 0
        animator?.cancel()
        val new = heightAnimator(final)
        animator = new
        new.start()
    }

    val final = if (expandedObs.value) {
        WRAP_CONTENT
    } else {
        0
    }
    layoutParams.height = final
    if (Build.VERSION.SDK_INT >= 18) {
        if (!isInLayout) {
            requestLayout()
        }
    } else {
        requestLayout()
    }

    return this
}