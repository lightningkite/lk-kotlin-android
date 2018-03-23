@file:JvmName("LkAndroidObservable")
@file:JvmMultifileClass

package lk.android.observable



import android.widget.TextView
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.lifecycle.bind

/**
 * Makes this [TextView] display the value of the observable.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun TextView.bindString(observable: ObservableProperty<String>) {
    lifecycle.bind(observable) {
        this.text = observable.value
    }
}

/**
 * Makes this [TextView] display the value of the observable.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T : Any> TextView.bindAny(observable: ObservableProperty<T>) {
    lifecycle.bind(observable) {
        this.text = observable.value.toString()
    }
}