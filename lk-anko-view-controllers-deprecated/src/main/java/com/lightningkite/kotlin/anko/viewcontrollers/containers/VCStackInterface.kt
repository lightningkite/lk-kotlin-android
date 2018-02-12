package com.lightningkite.kotlin.anko.viewcontrollers.containers

import com.lightningkite.kotlin.anko.animation.AnimationSet
import com.lightningkite.kotlin.anko.viewcontrollers.ViewController
import java.util.*

/**
 *
 * Created by shanethompson on 7/21/17.
 *
 */

@Deprecated("Deprecated along with ViewControllers in general.")
interface VCStackInterface<T : ViewController> : VCContainer {

    var defaultPushAnimation: AnimationSet
    var defaultPopAnimation: AnimationSet
    var defaultSwapAnimation: AnimationSet

    /**
     * Set the stack and update the view.
     */
    fun setStack(newStack: Stack<T>, animationSet: AnimationSet? = defaultPushAnimation)

    /**
     * Push a new controllers onto the stack.
     */
    fun push(viewController: T, animationSet: AnimationSet? = defaultPushAnimation)

    /**
     * Remove the top controllers off the stack.
     */
    fun pop(animationSet: AnimationSet? = defaultPopAnimation)

    /**
     * Pop all of the controllers except for the first one.
     */
    fun root(animationSet: AnimationSet? = defaultPopAnimation)

    /**
     * Pop controllers off the stack until [predicate] returns true.
     */
    fun back(predicate: (T) -> Boolean, animationSet: AnimationSet? = defaultPopAnimation)

    /**
     * Swap the top controller with another one.
     */
    fun swap(viewController: T, animationSet: AnimationSet? = defaultSwapAnimation)

    /**
     * Clear the stack and initiate the stack with viewController
     */
    fun reset(viewController: T, animationSet: AnimationSet? = defaultSwapAnimation)
}
