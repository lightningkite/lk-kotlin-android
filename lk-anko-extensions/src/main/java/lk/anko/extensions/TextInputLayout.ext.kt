package lk.anko.extensions

import android.support.annotation.StyleRes
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import org.jetbrains.anko.custom.ankoView

/**
 * Anko function for creating a MaterialDesign edit text.
 */
inline fun TextInputLayout.textInputEditText(init: TextInputEditText.() -> Unit) = ankoView({
    TextInputEditText(it)
}, 0, init)

/**
 * Anko function for creating a MaterialDesign edit text.
 */
inline fun TextInputLayout.themedTextInputEditText(@StyleRes theme: Int, init: TextInputEditText.() -> Unit) = ankoView({
    TextInputEditText(it)
}, theme, init)