@file:JvmName("LkAndroidAnimationsObservable")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko

import android.os.Build
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.lightningkite.kotlin.anko.animation.TypedValueAnimator
import com.lightningkite.kotlin.anko.animation.heightAnimator
import com.lightningkite.kotlin.observable.property.ObservableProperty
import com.lightningkite.kotlin.observable.property.listen


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