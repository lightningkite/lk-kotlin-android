@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko

import android.app.AlertDialog
import android.content.Context

/**
 * Various extension functions for activities.
 * Created by jivie on 4/12/16.
 */

/**
 * Opens a selector dialog.
 */
fun Context.selector(title: String?, pairs: List<Pair<String, () -> Unit>>) = selector(
        title = title,
        pairs = pairs,
        onCancel = {}
)

/**
 * Opens a selector dialog.
 */
fun Context.selector(title: String?, vararg pairs: Pair<String, () -> Unit>) = selector(
        title = title,
        pairs = pairs.toList(),
        onCancel = {}
)

/**
 * Opens a selector dialog.
 */
fun Context.selector(title: Int?, pairs: List<Pair<Int, () -> Unit>>) = selector(
        title = title?.let { resources.getString(it) },
        pairs = pairs.map { resources.getString(it.first) to it.second },
        onCancel = {}
)

/**
 * Opens a selector dialog.
 */
fun Context.selector(title: Int?, vararg pairs: Pair<Int, () -> Unit>) = selector(
        title = title?.let { resources.getString(it) },
        pairs = pairs.map { resources.getString(it.first) to it.second },
        onCancel = {}
)

inline fun Context.selector(title: Int?, vararg pairs: Pair<Int, () -> Unit>, crossinline onCancel: () -> Unit) = selector(
        title = title?.let { resources.getString(it) },
        pairs = pairs.map { resources.getString(it.first) to it.second },
        onCancel = onCancel
)

inline fun Context.selector(title: String?, pairs: List<Pair<String, () -> Unit>>, crossinline onCancel: () -> Unit) {
    AlertDialog.Builder(this)
            .apply { if (title != null) setTitle(title) }
            .setItems(pairs.map { it.first }.toTypedArray()) { di, it ->
                if (it >= 0) {
                    pairs[it].second()
                }
            }
            .setOnCancelListener {
                onCancel()
            }
            .show()
}