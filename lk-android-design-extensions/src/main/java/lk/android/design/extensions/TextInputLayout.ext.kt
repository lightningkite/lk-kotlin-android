@file:JvmName("LkAndroidDesignExtensions")
@file:JvmMultifileClass

package lk.android.design.extensions



import android.graphics.Typeface
import android.support.design.widget.TextInputLayout
import java.util.*

/**
 * Various convenience extensions for [TextInputLayout]
 * Created by josep on 3/3/2016.
 */

/**
 * Sets the text resource for the hint.
 */
var TextInputLayout.hintResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        hint = resources.getString(value)
    }
/**
 * Sets the text resource for the error.
 */
var TextInputLayout.errorResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        error = resources.getString(value)
    }

private val tilFontCache: HashMap<String, Typeface> = HashMap()
/**
 * Sets the font for the TextInputLayout.
 */
fun TextInputLayout.setFont(fileName: String) {
    setTypeface(tilFontCache.getOrPut(fileName) { Typeface.createFromAsset(context.assets, fileName) })
}