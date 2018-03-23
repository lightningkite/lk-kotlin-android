@file:JvmName("LkAndroidObservable")
@file:JvmMultifileClass

package lk.android.observable



import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import java.text.NumberFormat
import java.text.ParseException

abstract class TextWatcherAdapter : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the text here will be updated.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun EditText.bindString(observable: MutableObservableProperty<String>) {
    setText(observable.value)
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (observable.value != s) {
                observable.value = (s.toString())
            }
        }
    })
    lifecycle.bind(observable) {
        if (observable.value != text.toString()) {
            this.setText(observable.value)
        }
    }
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the text here will be updated.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun EditText.bindNullableString(observable: MutableObservableProperty<String?>) {
    setText(observable.value)
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (observable.value != s) {
                observable.value = (s.toString())
            }
        }
    })
    lifecycle.bind(observable) {
        if (observable.value != text.toString()) {
            this.setText(observable.value)
        }
    }
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the integer here will be updated.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun EditText.bindInt(observable: MutableObservableProperty<Int>, format: NumberFormat = NumberFormat.getNumberInstance()) {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    val originalTextColor = this.textColors.defaultColor
    var value: Int? = null
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            value = null

            try {
                value = format.parse(s.toString()).toInt()
            } catch (e: ParseException) {
                //do nothing.
            }

            try {
                value = s.toString().toInt()
            } catch (e: NumberFormatException) {
                //do nothing.
            }

            if (value == null) {
                setTextColor(0xFFFF0000.toInt())
            } else {
                setTextColor(originalTextColor)
                if (observable.value != value) {
                    observable.value = (value!!)
                }
            }
        }
    })
    lifecycle.bind(observable) {
        if (observable.value != value) {
            this.setText(format.format(observable.value))
        }
    }
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the integer here will be updated.
 */
inline fun EditText.bindNullableInt(observable: MutableObservableProperty<Int?>, format: NumberFormat = NumberFormat.getNumberInstance()) {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    var value: Int? = null
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            value = null
            if (!s.isNullOrBlank()) {
                try {
                    value = format.parse(s.toString()).toInt()
                } catch (e: ParseException) {
                    //do nothing.
                }

                try {
                    value = s.toString().toInt()
                } catch (e: NumberFormatException) {
                    //do nothing.
                }

            }

            if (observable.value != value) {
                observable.value = (value)
            }
        }
    })
    lifecycle.bind(observable) {
        if (observable.value != value) {
            if (observable.value == null) this.setText("")
            else this.setText(format.format(observable.value))
        }
    }
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the integer here will be updated.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun EditText.bindLong(observable: MutableObservableProperty<Long>, format: NumberFormat = NumberFormat.getNumberInstance()) {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    val originalTextColor = this.textColors.defaultColor
    var value: Long? = null
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            value = null

            try {
                value = format.parse(s.toString()).toLong()
            } catch (e: ParseException) {
                //do nothing.
            }

            try {
                value = s.toString().toLong()
            } catch (e: NumberFormatException) {
                //do nothing.
            }

            if (value == null) {
                setTextColor(0xFFFF0000.toInt())
            } else {
                setTextColor(originalTextColor)
                if (observable.value != value) {
                    observable.value = (value!!)
                }
            }
        }
    })
    lifecycle.bind(observable) {
        if (observable.value != value) {
            this.setText(format.format(observable.value))
        }
    }
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the integer here will be updated.
 */
inline fun EditText.bindNullableLong(observable: MutableObservableProperty<Long?>, format: NumberFormat = NumberFormat.getNumberInstance()) {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    var value: Long? = null
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            value = null
            if (!s.isNullOrBlank()) {
                try {
                    value = format.parse(s.toString()).toLong()
                } catch (e: ParseException) {
                    //do nothing.
                }

                try {
                    value = s.toString().toLong()
                } catch (e: NumberFormatException) {
                    //do nothing.
                }

            }

            if (observable.value != value) {
                observable.value = (value)
            }
        }
    })
    lifecycle.bind(observable) {
        if (observable.value != value) {
            if (observable.value == null) this.setText("")
            else this.setText(format.format(observable.value!!))
        }
    }
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the number here will be updated.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun EditText.bindNullableFloat(observable: MutableObservableProperty<Float?>, format: NumberFormat = NumberFormat.getNumberInstance()) {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    var value: Float? = null
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            value = null
            if (!s.isNullOrBlank()) {

                try {
                    value = format.parse(s.toString()).toFloat()
                } catch (e: ParseException) {
                    //do nothing.
                }

                try {
                    value = s.toString().toFloat()
                } catch (e: NumberFormatException) {
                    //do nothing.
                }
            }

            println("PRE value $value obs ${observable.value}")
            if (observable.value != value) {
                observable.value = (value)
            }
            println("PST value $value obs ${observable.value}")
        }
    })
    lifecycle.bind(observable) {
        if (observable.value != value) {
            if (observable.value == null) this.setText("")
            else this.setText(format.format(observable.value))
        }
    }
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the number here will be updated.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun EditText.bindNullableDouble(observable: MutableObservableProperty<Double?>, format: NumberFormat = NumberFormat.getNumberInstance()) {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    val originalTextColor = this.textColors.defaultColor
    var value: Double? = null
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            value = null

            if (!s.isNullOrBlank()) {
                try {
                    value = format.parse(s.toString()).toDouble()
                } catch (e: ParseException) {
                    //do nothing.
                }

                try {
                    value = s.toString().toDouble()
                } catch (e: NumberFormatException) {
                    //do nothing.
                }
            }

            setTextColor(originalTextColor)
            if (observable.value != value) {
                observable.value = (value)
            }
        }
    })

    lifecycle.bind(observable) {
        if (observable.value != value) {
            if (observable.value == null) this.setText("")
            else this.setText(format.format(observable.value!!))
        }
    }
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the number here will be updated.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun EditText.bindFloat(observable: MutableObservableProperty<Float>, format: NumberFormat = NumberFormat.getNumberInstance()) {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    val originalTextColor = this.textColors.defaultColor
    var value = Float.NaN
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            value = Float.NaN

            try {
                value = format.parse(s.toString()).toFloat()
            } catch (e: ParseException) {
                //do nothing.
            }

            try {
                value = s.toString().toFloat()
            } catch (e: NumberFormatException) {
                //do nothing.
            }

            if (value.isNaN()) {
                setTextColor(0xFFFF0000.toInt())
            } else {
                setTextColor(originalTextColor)
                if (observable.value != value) {
                    observable.value = (value)
                }
            }
        }
    })
    lifecycle.bind(observable) {
        if (observable.value != value) {
            this.setText(format.format(observable.value))
        }
    }
}

/**
 * Binds this [EditText] two way to the observable.
 * When the user edits this, the value of the observable will change.
 * When the value of the observable changes, the number here will be updated.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun EditText.bindDouble(observable: MutableObservableProperty<Double>, format: NumberFormat = NumberFormat.getNumberInstance()) {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    val originalTextColor = this.textColors.defaultColor
    var value = Double.NaN
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            value = Double.NaN

            try {
                value = format.parse(s.toString()).toDouble()
            } catch (e: ParseException) {
                //do nothing.
            }

            try {
                value = s.toString().toDouble()
            } catch (e: NumberFormatException) {
                //do nothing.
            }

            if (value.isNaN()) {
                setTextColor(0xFFFF0000.toInt())
            } else {
                setTextColor(originalTextColor)
                if (observable.value != value) {
                    observable.value = (value)
                }
            }
        }
    })
    lifecycle.bind(observable) {
        if (observable.value != value) {
            this.setText(format.format(observable.value))
        }
    }
}
