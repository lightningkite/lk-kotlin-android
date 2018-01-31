package com.lightningkite.kotlin.anko.networking.image

import android.content.Context
import android.graphics.Bitmap
import com.lightningkite.kotlin.anko.async.UIThread
import com.lightningkite.kotlin.async.Async
import com.lightningkite.kotlin.async.invokeOn
import com.lightningkite.kotlin.async.thenOn
import com.lightningkite.kotlin.networking.TypedResponse
import okhttp3.Request
import java.io.Closeable

/**
 * Loads images with a cache.
 * Created by joseph on 8/3/17.
 */
class ImageLoader(val baseRequest: Request.Builder, val imageMaxWidth: Int, val imageMaxHeight: Int) : Closeable {
    val requests = HashSet<String>()
    val callbacks = HashMap<String, ArrayList<(TypedResponse<Bitmap>) -> Unit>>()
    val results = HashMap<String, Bitmap>()

    fun getImage(context: Context, input: String, callback: (TypedResponse<Bitmap>) -> Unit) {
        if (results.containsKey(input)) {
            callback.invoke(TypedResponse(200, results[input]!!))
        } else {
            callbacks.getOrPut(input) { ArrayList() }.add(callback)
            if (!requests.contains(input)) {
                requests.add(input)
                baseRequest.url(input).lambdaBitmapExif(context, imageMaxWidth, imageMaxHeight).thenOn(UIThread) { out ->
                    if (out.isSuccessful())
                        results[input] = out.result!!
                    callbacks[input]?.forEach { it.invoke(out) }
                    callbacks.remove(input)
                    requests.remove(input)
                }.invokeOn(Async)
            }
        }
    }

    override fun close() {
        for ((key, image) in results) {
            image.recycle()
        }
        results.clear()
    }
}