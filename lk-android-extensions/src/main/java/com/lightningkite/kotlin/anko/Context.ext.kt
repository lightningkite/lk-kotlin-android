@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*

/**
 * Extension functions for Context
 * Created by jivie on 6/1/16.
 */

fun Context.getActivity(): Activity? = when {
    this is Activity -> this
    this is ContextWrapper -> baseContext.getActivity()
    else -> null
}

/**
 * Gets a unique id for this instance of the application.  Doesn't persist across installs.
 */
fun Context.getUniquePreferenceId(): String {
    val key = "com.lightningkite.kotlincomponents.device.install_uuid"
    val sharedPreferences = getSharedPreferences("com.lightningkite.kotlin.anko", 0)
    val found: String? = sharedPreferences.getString(key, null)
    if (found != null) return found
    val made = UUID.randomUUID().toString()
    sharedPreferences.edit().putString(key, made).apply()
    return made
}

/**
 * Opens a time picker dialog.
 */
inline fun Context.timePicker(start: Calendar, crossinline after: (Calendar) -> Unit) {
    TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        start.set(Calendar.HOUR_OF_DAY, hourOfDay)
        start.set(Calendar.MINUTE, minute)
        after(start)
    }, start.get(Calendar.HOUR_OF_DAY), start.get(Calendar.MINUTE), false).show()
}

/**
 * Opens a date picker dialog.
 */
inline fun Context.datePicker(start: Calendar, crossinline after: (Calendar) -> Unit) {
    DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                start.set(Calendar.YEAR, year)
                start.set(Calendar.MONTH, monthOfYear)
                start.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                after(start)
            },
            start.get(Calendar.YEAR),
            start.get(Calendar.MONTH),
            start.get(Calendar.DAY_OF_MONTH)
    ).show()
}

/**
 * Hides the soft keyboard.
 */
fun Context.hideSoftInput() {
    val activity = this.getActivity() ?: return
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}