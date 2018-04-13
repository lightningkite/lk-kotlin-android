@file:JvmName("LkAnkoAnimations")
@file:JvmMultifileClass

package lk.anko.animations

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.FrameLayout
import lk.android.animations.SwapView
import lk.android.animations.TransitionView
import org.jetbrains.anko.custom.ankoView

/**
 * Anko function for [SwapView]
 */
inline fun ViewManager.swapView(init: SwapView.() -> Unit) = ankoView(::SwapView, 0, init)

/**
 * Anko function for [TransitionView]
 */
inline fun ViewManager.transitionView(init: _TransitionView.() -> Unit): _TransitionView = ankoView(::_TransitionView, 0, init)

/**
 * Anko version of [TransitionView].
 */
@Suppress("NOTHING_TO_INLINE", "ClassName")
class _TransitionView(context: Context) : TransitionView(context) {

    inline fun <T : View> T.lparams(
            c: Context?,
            attrs: AttributeSet?,
            init: FrameLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
            c: Context?,
            attrs: AttributeSet?
    ): T {
        val layoutParams = FrameLayout.LayoutParams(c!!, attrs!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: FrameLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
    ): T {
        val layoutParams = FrameLayout.LayoutParams(width, height)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            gravity: Int,
            init: FrameLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(width, height, gravity)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            gravity: Int
    ): T {
        val layoutParams = FrameLayout.LayoutParams(width, height, gravity)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
            source: ViewGroup.LayoutParams?,
            init: FrameLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
            source: ViewGroup.LayoutParams?
    ): T {
        val layoutParams = FrameLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
            source: ViewGroup.MarginLayoutParams?,
            init: FrameLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    inline fun <T : View> T.lparams(
            source: ViewGroup.MarginLayoutParams?
    ): T {
        val layoutParams = FrameLayout.LayoutParams(source!!)
        this@lparams.layoutParams = layoutParams
        return this
    }
}