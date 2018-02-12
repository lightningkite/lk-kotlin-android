package com.lightningkite.kotlin.anko.viewcontrollers.implementations

import android.view.View
import android.view.ViewGroup
import com.lightningkite.kotlin.anko.activity.ViewGenerator
import com.lightningkite.kotlin.anko.animation.AnimationSet
import com.lightningkite.kotlin.anko.viewcontrollers.VCContext
import com.lightningkite.kotlin.anko.viewcontrollers.containers.VCContainer
import java.io.Closeable

/**
 * Embeds the given view container in the given view, transitioning new views in and out as needed.
 *
 * Created by joseph on 11/7/16.
 */
@Deprecated("Deprecated along with ViewControllers in general.")
class VCContainerEmbedder(val vcContext: VCContext, val root: ViewGroup, val container: VCContainer, val makeLayoutParams: () -> ViewGroup.LayoutParams) : Closeable {

    var defaultAnimation: AnimationSet? = AnimationSet.fade

    var wholeViewAnimatingIn: Boolean = false
    var killViewAnimateOutCalled: Boolean = false

    var current: Any? = null
    var currentView: View? = null
    val swap = fun(new: ViewGenerator) {
        val oldView = currentView
        val old = current
        val animation = defaultAnimation
        current = new
        val newView = new.invoke(vcContext)
        root.addView(newView, makeLayoutParams())
        currentView = newView
        if (old != null && oldView != null) {
            if (animation == null) {
                root.removeView(oldView)
            } else {
                val animateOut = animation.animateOut
                oldView.animateOut(root).withEndAction {
                    root.removeView(oldView)
                }.start()
                val animateIn = animation.animateIn
                newView.animateIn(root).start()
            }
        }
        killViewAnimateOutCalled = false
    }

    init {
        container.add(swap)
        swap.invoke(container.value)
//        container.swapListener = swap
//        swap(container.current, null) {}
    }

    override fun close() {
        container.remove(swap)
    }

    fun unmake() {
        if (!killViewAnimateOutCalled) {
            killViewAnimateOutCalled = true
        }
        if (currentView != null) {
            root.removeView(currentView)
        }
        current = null
        currentView = null
    }


}