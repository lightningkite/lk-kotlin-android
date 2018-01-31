@file:JvmName("LkAndroidDesignExtensions")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko

import android.graphics.Typeface
import android.support.design.widget.TextInputLayout
import java.util.*

/**
 * Various convenience extensions for [TextInputLayout]
 * Created by josep on 3/3/2016.
 */

var TextInputLayout.hintResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        hint = resources.getString(value)
    }
var TextInputLayout.errorResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        error = resources.getString(value)
    }

private val tilFontCache: HashMap<String, Typeface> = HashMap()
fun TextInputLayout.setFont(fileName: String) {
    setTypeface(tilFontCache.getOrPut(fileName) { Typeface.createFromAsset(context.assets, fileName) })
}