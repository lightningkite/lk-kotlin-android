@file:JvmName("LkAndroidObservable")
@file:JvmMultifileClass

package lk.android.observable



import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.list.ObservableList
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.lifecycle.bind

/**
 * Binds this [Spinner] two way to the observable.
 * When the user picks a new value from the spinner, the value of the observable will change to the index of the new value.
 * When the value of the observable changes, the item will shown will be updated.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Spinner.bindIndex(observable: MutableObservableProperty<Int>) {
    lifecycle.bind(observable) {
        if (selectedItemPosition != it) {
            setSelection(it)
        }
    }
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (position != observable.value) {
                observable.value = (position)
            }
        }

    }
}

//TODO: The remaining binds may be removable via observable manipulation

@Suppress("NOTHING_TO_INLINE")
inline fun <T> Spinner.bindList(observable: MutableObservableProperty<T>, list: List<T>) {
    lifecycle.bind(observable) {
        val index = list.indexOf(it)
        if (index == -1) return@bind
        setSelection(index)
    }
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            observable.value = (list[position])
        }
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun <T, E> Spinner.bindList(observable: MutableObservableProperty<T>, list: List<E>, crossinline conversion: (E) -> T) {
    lifecycle.bind(observable) {
        val index = list.indexOfFirst { item -> it == conversion(item) }
        if (index == -1) return@bind
        setSelection(index)
    }
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            observable.value = (conversion(list[position]))
        }
    }
}


@Suppress("NOTHING_TO_INLINE")
inline fun <T> Spinner.bindList(observable: MutableObservableProperty<T>, list: ObservableList<T>) {
    lifecycle.bind(observable, list.onUpdate) { it, list ->
        val index = list.indexOf(it)
        if (index == -1) return@bind
        setSelection(index)
    }
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            observable.value = (list[position])
        }
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun <T, E> Spinner.bindList(observable: MutableObservableProperty<T>, list: ObservableList<E>, crossinline conversion: (E) -> T) {
    lifecycle.bind(observable, list.onUpdate) { it, list ->
        val index = list.indexOfFirst { item -> it == conversion(item) }
        if (index == -1) return@bind
        setSelection(index)
    }
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            observable.value = (conversion(list[position]))
        }
    }
}


@Suppress("NOTHING_TO_INLINE")
inline fun <T> Spinner.bindList(observable: MutableObservableProperty<T>, listObs: ObservableProperty<List<T>>) {
    lifecycle.bind(observable, listObs) { it, list ->
        val index = list.indexOf(it)
        if (index == -1) return@bind
        setSelection(index)
    }
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            observable.value = (listObs.value[position])
        }
    }
}


@Suppress("NOTHING_TO_INLINE")
inline fun <T> Spinner.bindListOpt(observable: MutableObservableProperty<T?>, listObs: ObservableProperty<List<T>>) {
    lifecycle.bind(observable, listObs) { it, list ->
        val index = list.indexOf(it)
        if (index == -1) return@bind
        setSelection(index)
    }
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            observable.value = (listObs.value[position])
        }
    }
}
