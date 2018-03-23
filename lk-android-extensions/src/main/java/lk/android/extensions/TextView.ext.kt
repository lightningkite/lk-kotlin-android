@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package lk.android.extensions



import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.widget.TextView
import java.util.*

/**
 * Extensions for TextView.
 * Created by josep on 3/3/2016.
 */

/**
 * Sets the text color by color resource.
 */
var TextView.textColorResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        setTextColor(resources.getColorCompat(value))
    }

/**
 * Sets the hint text color by color resource.
 */
var TextView.hintTextColorResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        setHintTextColor(resources.getColorCompat(value))
    }

/**
 * Sets the text color states by color state list resource.
 */
var TextView.textColorsResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        setTextColor(resources.getColorStateListCompat(value))
    }

/**
 * Sets the hint color states by color state list resource.
 */
var TextView.hintTextColorsResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        setHintTextColor(resources.getColorStateListCompat(value))
    }

/**
 * A cache of fonts by name.
 */
val fontCache: HashMap<String, Typeface> = HashMap()

/**
 * Sets the font of a text view by asset file name.
 */
fun TextView.setFont(fileName: String) {
    typeface = fontCache.getOrPut(fileName) { Typeface.createFromAsset(context.assets, fileName) }
}

/**
 * Sets the compound drawables, but since its' in Kotlin, it can provide defaults.
 */
fun TextView.setCompoundDrawablesWithIntrinsicBoundsKotlin(
        left: Int = 0,
        top: Int = 0,
        right: Int = 0,
        bottom: Int = 0
) = setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)

/**
 * Sets the compound drawables, but since it's in Kotlin, it can provide defaults.
 */
fun TextView.setCompoundDrawablesWithIntrinsicBoundsKotlin(
        left: Drawable? = null,
        top: Drawable? = null,
        right: Drawable? = null,
        bottom: Drawable? = null
) = setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)