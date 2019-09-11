@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package lk.android.extensions



import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

/**
 * Compat wrappers
 * Created by jivie on 5/4/16.
 */

/**
 * Gets a color from [Resources].
 * @see [ResourcesCompat.getColor]
 */
fun Resources.getColorCompat(resource: Int): Int = ResourcesCompat.getColor(this, resource, null)

/**
 * Gets a drawable from [Resources].
 * @see [ResourcesCompat.getDrawable]
 */
fun Resources.getDrawableCompat(resource: Int): Drawable = ResourcesCompat.getDrawable(this, resource, null)!!

/**
 * Gets a color state list from [Resources].
 * @see [ResourcesCompat.getColorStateList]
 */
fun Resources.getColorStateListCompat(resource: Int): ColorStateList = ResourcesCompat.getColorStateList(this, resource, null)!!