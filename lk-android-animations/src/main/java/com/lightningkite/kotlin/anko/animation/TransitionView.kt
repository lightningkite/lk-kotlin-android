package com.lightningkite.kotlin.anko.animation

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.FrameLayout
import java.util.*

/**
 * A container view that transitions between the child views.
 * Use this class Anko-style, and append the views with the [tag] function.
 * Created by jivie on 8/26/15.
 */
open class TransitionView(context: Context) : FrameLayout(context) {

    class AnimationInfo(
            val inView: View,
            val inAnimator: ViewPropertyAnimator?,
            val outView: View?,
            val outAnimator: ViewPropertyAnimator?
    )

    val onViewChange = ArrayList<(AnimationInfo) -> Unit>()

    private val views: HashMap<String, View> = HashMap()
    private var currentView: View? = null
    var defaultAnimation: AnimationSet = AnimationSet.fade
    var animateOutToVisibilityState: Int = View.INVISIBLE

    override fun addView(child: View) {
        super.addView(child)
        child.visibility = animateOutToVisibilityState
    }

    override fun generateDefaultLayoutParams(): LayoutParams? {
        val params = super.generateDefaultLayoutParams()
        params.gravity = Gravity.CENTER
        return params
    }

    /**
     * Tags a view with [myTag].
     * @param myTag The tag used to access this view later.
     */
    fun <T : View> T.tag(myTag: String): T {
        views.put(myTag, this)
        visibility = animateOutToVisibilityState
        return this
    }

    /**
     * Animates using [set] to the view designated by [tag].
     * @param tag The view to animate to.  You can tag views using [tag].
     * @param set The animation set for animating.
     */
    fun animate(tag: String, set: AnimationSet = defaultAnimation) = animate(views[tag]
            ?: throw IllegalArgumentException(), set)

    inline fun animate(index: Int, set: AnimationSet = defaultAnimation) = animate(getChildAt(index), set)

    /**
     * Animates using [set] to the given view.
     * @param view The view to animate to.
     * @param set The animation set for animating.
     */
    fun animate(newView: View, set: AnimationSet = defaultAnimation) {
        if (newView == currentView) return
        val oldView = currentView
        if (oldView == null) {
            jump(newView)
        } else {
            val animateIn = set.animateIn
            val animateOut = set.animateOut

            newView.visibility = View.VISIBLE
            val newAnimation = newView.animateIn(this)
            val oldAnimation = oldView.animateOut(this).withEndAction {
                oldView.visibility = animateOutToVisibilityState
            }
            val info = (AnimationInfo(
                    inView = newView,
                    inAnimator = newAnimation,
                    outView = oldView,
                    outAnimator = oldAnimation
            ))
            onViewChange.forEach { it.invoke(info) }
            oldAnimation.start()
            newAnimation.start()

            currentView = newView
        }
    }

    fun jump(view: View) {
        val oldView = currentView
        currentView?.visibility = animateOutToVisibilityState
        currentView = view
        view.visibility = View.VISIBLE
        val info = AnimationInfo(
                inView = view,
                inAnimator = null,
                outView = oldView,
                outAnimator = null
        )
        onViewChange.forEach { it.invoke(info) }
    }

    fun jump(tag: String) = jump(views[tag] ?: throw IllegalArgumentException())
    inline fun jump(index: Int) = jump(getChildAt(index))


    fun animateHeight() {
        onViewChange += {
            if (it.inAnimator != null) {
                val size = it.inView.run {
                    this.measure(
                            View.MeasureSpec.makeMeasureSpec((parent as ViewGroup).width, View.MeasureSpec.AT_MOST),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                    )
                    measuredHeight
                }
                heightAnimator(size).setDuration(it.inAnimator.duration).start()
            } else {
                layoutParams.height = it.inView.run {
                    this.measure(
                            View.MeasureSpec.makeMeasureSpec((parent as ViewGroup).width, View.MeasureSpec.AT_MOST),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                    )
                    measuredHeight
                }
            }
        }
    }
}

