package com.lightningkite.kotlin.anko.async

import android.os.Handler
import android.os.Looper

/**
 * Various functions to do things asynchronously.
 * Created by jivie on 9/2/15.
 */

@Deprecated("Use UIThread instead")
object AndroidAsync {
    val uiHandler: Handler = Handler(Looper.getMainLooper())

    fun sendToThread(action: () -> Unit) {
        uiHandler.post(action)
    }

    fun init() {
    }
}
