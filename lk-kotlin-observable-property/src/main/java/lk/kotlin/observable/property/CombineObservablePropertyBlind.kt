package lk.kotlin.observable.property




/**
 * Combines several observable properties into one, ignoring the values.
 * Created by joseph on 12/2/16.
 */
@Suppress("UNCHECKED_CAST")
class CombineObservablePropertyBlind<T>(
        val observables: Collection<ObservableProperty<*>>,
        val combine: () -> T
) : EnablingMutableCollection<(T) -> Unit>(), ObservableProperty<T> {

    constructor(vararg observables: ObservableProperty<*>, combine: () -> T) : this(observables.toList(), combine)

    override var value = combine()

    fun update() {
        value = combine()
        forEach { it.invoke(value) }
    }

    val callbacks = HashMap<ObservableProperty<Any?>, (Any?) -> Unit>()

    override fun enable() {
        callbacks += observables.map {
            val newListener = { _: Any? -> update() }
            val itFake = it as ObservableProperty<Any?>
            itFake.add(newListener)
            itFake to newListener
        }
    }

    override fun disable() {
        callbacks.forEach { (key, value) -> key.remove(value) }
        callbacks.clear()
    }
}