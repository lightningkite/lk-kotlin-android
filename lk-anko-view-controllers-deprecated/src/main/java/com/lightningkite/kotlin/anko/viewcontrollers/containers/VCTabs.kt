package com.lightningkite.kotlin.anko.viewcontrollers.containers

import com.lightningkite.kotlin.anko.viewcontrollers.ViewController
import com.lightningkite.kotlin.lambda.invokeAll
import com.lightningkite.kotlin.observable.property.ObservableProperty
import java.util.*

/**
 * Used to create left/right tabs.
 * @param startIndex The first view controller's index to show.
 * @param vcs The view controllers to display.
 * Created by jivie on 10/14/15.
 */
@Deprecated("Use a integer observable with a mapper when binding.")
class VCTabs(startIndex: Int, vcs: List<ViewController>) : ObservableProperty<ViewController> {

    constructor(startIndex: Int, vararg vcs: ViewController) : this(startIndex, vcs.toList())

    val viewControllers: Array<ViewController> = Array(vcs.size, { vcs[it] })
    val onIndexChange = ArrayList<(Int) -> Unit>()
    var index: Int = startIndex
        set(value) {
            if (value == field) return
            field = value
            listeners.invokeAll(viewControllers[value])
        }

    override val value: ViewController get() = viewControllers[index]

    fun swap(newIndex: Int) {
        index = newIndex
    }

    val listeners = ArrayList<(ViewController) -> Unit>()
    override fun add(element: (ViewController) -> Unit): Boolean = listeners.add(element)
    override fun remove(element: (ViewController) -> Unit): Boolean = listeners.remove(element)
}