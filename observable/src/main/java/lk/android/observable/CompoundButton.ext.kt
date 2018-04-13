@file:JvmName("LkAndroidObservable")
@file:JvmMultifileClass

package lk.android.observable


import android.widget.CompoundButton
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.lifecycle.bind


/**
 * Binds this view to the value of the [observable], such that this view and the observable
 * always reflect each others values.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun CompoundButton.bindBoolean(observable: MutableObservableProperty<Boolean>) {
    this.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
        if (isChecked != observable.value) {
            observable.value = (isChecked)
        }
    }
    lifecycle.bind(observable) {
        val value = observable.value
        if (isChecked != value) {
            isChecked = value
        }
    }
}

/**
 * Binds this view to the value of the [observable], such that this view and the observable
 * always reflect each others values.
 */
@Suppress("NOTHING_TO_INLINE")
@JvmName("bindBooleanOptional")
inline fun CompoundButton.bindBoolean(observable: MutableObservableProperty<Boolean?>, default: Boolean = false) {
    this.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
        if (isChecked != observable.value) {
            observable.value = (isChecked)
        }
    }
    lifecycle.bind(observable) {
        val value = observable.value
        if (isChecked != value) {
            isChecked = value ?: default
        }
    }
}