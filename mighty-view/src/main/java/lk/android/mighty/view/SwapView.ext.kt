package lk.android.mighty.view

import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.animations.AnimationSet
import lk.android.animations.SwapView
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.StackObservableProperty
import lk.kotlin.observable.property.lifecycle.bind

/**
 * Binds a [SwapView] to an observable with [ViewGenerator]s - in other words, the view in the
 * [SwapView] will always match the view inside the observable.
 */
fun <T : (ActivityAccess) -> View> SwapView.bind(
        access: ActivityAccess,
        observable: ObservableProperty<T>,
        getAnimation: (T) -> AnimationSet = { AnimationSet.fade }
) {
    lifecycle.bind(observable) {
        swap(it.invoke(access), getAnimation(it))
    }
}

/**
 * Binds a [SwapView] to a stack observable with [ViewGenerator]s - in other words, the view in the
 * [SwapView] will always match the view inside the observable.  The animation will pop and push
 * accordingly.
 */
fun <T : (ActivityAccess) -> View> SwapView.bind(
        access: ActivityAccess,
        observable: StackObservableProperty<T>,
        pushAnimationSet: AnimationSet = AnimationSet.slidePush,
        neutralAnimationSet: AnimationSet = AnimationSet.fade,
        popAnimationSet: AnimationSet = AnimationSet.slidePop
) {
    var previousSize = observable.stack.size
    bind(
            access = access,
            observable = observable,
            getAnimation = {
                val diff = observable.stack.size - previousSize
                previousSize = observable.stack.size
                if (diff > 0) pushAnimationSet
                else if (diff < 0) popAnimationSet
                else neutralAnimationSet
            }
    )
}