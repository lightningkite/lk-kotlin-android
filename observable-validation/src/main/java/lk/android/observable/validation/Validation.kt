@file:JvmName("LkAndroidObservableValidation")
@file:JvmMultifileClass

package lk.android.observable.validation

import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import android.widget.TextView
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.lifecycle.listen
import java.util.*

/**
 * An extension on an [ObservableProperty] which evaluates if the value is valid when asked.
 */
class Validation<T>(
        val observableProperty: ObservableProperty<T>,
        val getError: Validation<T>.(T) -> StringFetcher?
) {
    val onCheck = ArrayList<(StringFetcher?) -> Unit>()

    fun invokeSilent(): StringFetcher? = getError.invoke(this, observableProperty.value)
    operator fun invoke(): StringFetcher? {
        val result = getError.invoke(this, observableProperty.value)
        onCheck.forEach { it.invoke(result) }
        return result
    }

    fun isValid(): Boolean = invoke() == null

    fun string(data: String) = StringFetcherDirect(data)
    fun resource(@StringRes resource: Int) = StringFetcherResource(resource)
}


private val ObservableProperty_Validation = WeakHashMap<ObservableProperty<*>, Validation<*>>()

/**
 * Gets or sets the validation attached to the observable.
 */
@Suppress("UNCHECKED_CAST")
var <T> ObservableProperty<T>.validation: Validation<T>?
    get() = ObservableProperty_Validation[this] as? Validation<T>
    set(value) {
        ObservableProperty_Validation[this] = value
    }


/**
 * Adds a validation to the observable.
 */
fun <T, P : ObservableProperty<T>> P.withValidation(getError: Validation<T>.(T) -> StringFetcher?): P {
    validation = Validation(this, getError)
    return this
}

/**
 * Binds a [TextView] to the error that validation of [observableProperty] reports.
 */
fun TextView.bindError(observableProperty: ObservableProperty<*>) {
    lifecycle.listen(observableProperty.validation!!.onCheck) { errorFetcher ->
        error = errorFetcher?.invoke(resources)
        if (errorFetcher != null) {
            requestFocus()
        }
    }
}

/**
 * Binds a [TextInputLayout] to the error that validation of [observableProperty] reports.
 */
fun TextInputLayout.bindError(observableProperty: ObservableProperty<*>) {
    lifecycle.listen(observableProperty.validation!!.onCheck) { errorFetcher ->
        error = errorFetcher?.invoke(resources)
        if (errorFetcher != null) {
            for (childIndex in 0 until this.childCount) {
                val child = this.getChildAt(childIndex)
                if (child is TextView) {
                    child.requestFocus()
                    break
                }
            }
        }
    }
}