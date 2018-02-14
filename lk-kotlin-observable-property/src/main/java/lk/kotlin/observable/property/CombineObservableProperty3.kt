package lk.kotlin.observable.property



/**
 * Combines several observable properties into one.
 * Created by joseph on 12/2/16.
 */
class CombineObservableProperty3<A, B, C, T>(
        val observableA: ObservableProperty<A>,
        val observableB: ObservableProperty<B>,
        val observableC: ObservableProperty<C>,
        val combine: (A, B, C) -> T
) : EnablingMutableCollection<(T) -> Unit>(), ObservableProperty<T> {

    override var value = combine(observableA.value, observableB.value, observableC.value)

    fun update() {
        value = combine(observableA.value, observableB.value, observableC.value)
        forEach { it.invoke(value) }
    }

    val callbackA = { item: A ->
        update()
    }
    val callbackB = { item: B ->
        update()
    }
    val callbackC = { item: C ->
        update()
    }

    override fun enable() {
        observableA.add(callbackA)
        observableB.add(callbackB)
        observableC.add(callbackC)
    }

    override fun disable() {
        observableA.remove(callbackA)
        observableB.remove(callbackB)
        observableC.remove(callbackC)
    }
}