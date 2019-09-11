@file:JvmName("LkAndroidAnimations")
@file:JvmMultifileClass

package lk.android.animations


import android.animation.Animator
import androidx.core.view.ViewCompat
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

/**
 * Creates an animator that will animate from the current height to a new height.
 */
fun View.heightAnimator(toHeight: Int): TypedValueAnimator.IntAnimator {
    val currentHeight = layoutParams.height.let {
        when (it) {
            WRAP_CONTENT, MATCH_PARENT -> height
            else -> it
        }
    }
    val fixedToHeight = when (toHeight) {
        WRAP_CONTENT -> {
            measure(
                    View.MeasureSpec.makeMeasureSpec((parent as? View)?.width
                            ?: (Int.MAX_VALUE / 2 - 1), View.MeasureSpec.AT_MOST),
                    View.MeasureSpec.makeMeasureSpec(Int.MAX_VALUE / 2 - 1, View.MeasureSpec.AT_MOST)
            )
            measuredHeight
        }
        else -> toHeight
    }
    return TypedValueAnimator.IntAnimator(currentHeight, fixedToHeight).onUpdate {
        layoutParams.height = it
        if (!ViewCompat.isInLayout(this@heightAnimator)) requestLayout()
    }.apply {
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                layoutParams.height = toHeight
            }
        })
    }
}

/**
 * Creates an animator that will animate from the current width to a new width.
 */
fun View.widthAnimator(toWidth: Int): TypedValueAnimator.IntAnimator {
    val currentWidth = layoutParams.width.let {
        when (it) {
            WRAP_CONTENT, MATCH_PARENT -> {
                measure(
                        View.MeasureSpec.makeMeasureSpec(Int.MAX_VALUE / 2 - 1, View.MeasureSpec.AT_MOST),
                        View.MeasureSpec.makeMeasureSpec((parent as? View)?.height
                                ?: (Int.MAX_VALUE / 2 - 1), View.MeasureSpec.AT_MOST)
                )
                measuredHeight
            }
            else -> it
        }
    }
    val fixedToWidth = when (toWidth) {
        WRAP_CONTENT -> {
            measure(
                    View.MeasureSpec.makeMeasureSpec(Int.MAX_VALUE / 2 - 1, View.MeasureSpec.AT_MOST),
                    View.MeasureSpec.makeMeasureSpec((parent as? View)?.height
                            ?: (Int.MAX_VALUE / 2 - 1), View.MeasureSpec.AT_MOST)
            )
            measuredHeight
        }
        else -> toWidth
    }
    return TypedValueAnimator.IntAnimator(currentWidth, fixedToWidth).onUpdate {
        layoutParams.width = it
        if (!ViewCompat.isInLayout(this@widthAnimator)) requestLayout()
    }
}