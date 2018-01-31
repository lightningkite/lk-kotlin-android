package com.lightningkite.kotlin.anko.networking.image

import android.graphics.Bitmap
import java.io.Closeable
import java.io.File

/**
 * Holds a temporary file and bitmap.
 * Created by jivie on 6/30/16.
 */
class BitmapHolder(val file: File, val bitmap: Bitmap) : Closeable {
    override fun close() {
        file.delete()
        bitmap.recycle()
    }
}