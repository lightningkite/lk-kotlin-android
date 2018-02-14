@file:JvmName("LkAndroidObservable")
@file:JvmMultifileClass

package lk.android.observable



import android.widget.TextView
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.lifecycle.bind

/**
 * Makes this [TextView] display the value of the bond.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun TextView.bindString(bond: ObservableProperty<String>) {
    lifecycle.bind(bond) {
        this.text = bond.value
    }
}

/**
 * Makes this [TextView] display the value of the bond.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T : Any> TextView.bindAny(bond: ObservableProperty<T>) {
    lifecycle.bind(bond) {
        this.text = bond.value.toString()
    }
}