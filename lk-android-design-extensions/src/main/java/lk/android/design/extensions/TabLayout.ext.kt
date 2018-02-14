@file:JvmName("LkAndroidDesignExtensions")
@file:JvmMultifileClass

package lk.android.design.extensions



import android.support.design.widget.TabLayout

/**
 * Various functions that are missing from TabLayout to use it programmatically.
 *
 * Created by jivie on 4/28/16.
 */

fun TabLayout.setTabTextSize(sp: Float) {
    val field = TabLayout::class.java.getDeclaredField("mTabTextSize")
    field.isAccessible = true
    field.set(this, (sp * resources.displayMetrics.scaledDensity).toInt())
}

fun TabLayout.setTabBackgroundResource(res: Int) {
    val field = TabLayout::class.java.getDeclaredField("mTabBackgroundResId")
    field.isAccessible = true
    field.set(this, res)
}