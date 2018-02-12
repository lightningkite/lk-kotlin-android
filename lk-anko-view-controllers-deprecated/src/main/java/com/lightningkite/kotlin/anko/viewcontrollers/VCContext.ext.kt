package com.lightningkite.kotlin.anko.viewcontrollers

import android.content.Intent
import android.os.Bundle
import org.jetbrains.anko.bundleOf

@Deprecated("Use the one in the anko package", ReplaceWith("this.startIntent(intent, options, onResult)", "com.lightningkite.kotlin.anko.activity"))
fun VCContext.startIntent(intent: Intent, options: Bundle = bundleOf(), onResult: (Int, Intent?) -> Unit = { _, _ -> }) {
    activity?.startActivityForResult(intent, prepareOnResult(onResult = onResult), options)
}