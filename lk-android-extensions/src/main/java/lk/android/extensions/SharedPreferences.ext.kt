@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package lk.android.extensions



import android.annotation.SuppressLint
import android.content.SharedPreferences

/**
 * Extensions for SharedPreferences
 *
 * Created by jivie on 6/3/16.
 */

/**
 * Returns true only the first time it is called for a particular key.
 * Can be reset by deleting the given key.
 */
@SuppressLint("ApplySharedPref")
fun SharedPreferences.once(key: String): Boolean {
    if (getBoolean(key, false)) {
        return false
    } else {
        edit().putBoolean(key, true).commit()
        return true
    }
}