@file:JvmName("LkAndroidDesignExtensions")
@file:JvmMultifileClass

package lk.android.design.extensions



import com.google.android.material.tabs.TabLayout

/**
 * Various functions that are missing from TabLayout to use it programmatically.
 *
 * Created by jivie on 4/28/16.
 */

/**
 * Sets the tab text size.
 */
fun TabLayout.setTabTextSize(sp: Float) {
    val field = TabLayout::class.java.getDeclaredField("mTabTextSize")
    field.isAccessible = true
    field.set(this, (sp * resources.displayMetrics.scaledDensity).toInt())
}

/**
 * Sets the tab background resource.
 */
fun TabLayout.setTabBackgroundResource(res: Int) {
    val field = TabLayout::class.java.getDeclaredField("mTabBackgroundResId")
    field.isAccessible = true
    field.set(this, res)
}