package com.lightningkite.kotlin.observable.property

/**
 * Combines several observable properties into one.
 * Created by joseph on 12/2/16.
 */
class CombineObservableProperty2<A, B, T>(
        val observableA: ObservableProperty<A>,
        val observableB: ObservableProperty<B>,
        val combine: (A, B) -> T
) : EnablingMutableCollection<(T) -> Unit>(), ObservableProperty<T> {

    override var value = combine(observableA.value, observableB.value)

    fun update() {
        value = combine(observableA.value, observableB.value)
        forEach { it.invoke(value) }
    }

    val callbackA = { item: A ->
        update()
    }
    val callbackB = { item: B ->
        update()
    }

    override fun enable() {
        observableA.add(callbackA)
        observableB.add(callbackB)
    }

    override fun disable() {
        observableA.remove(callbackA)
        observableB.remove(callbackB)
    }
}