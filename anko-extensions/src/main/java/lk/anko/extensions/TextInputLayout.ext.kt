package lk.anko.extensions

import androidx.annotation.StyleRes
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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