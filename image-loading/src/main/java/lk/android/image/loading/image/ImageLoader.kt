package lk.android.image.loading.image


import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import lk.android.extensions.image.getBitmapFromUri
import lk.android.ui.thread.UIThread
import lk.kotlin.jvm.utils.async.Async
import lk.kotlin.jvm.utils.async.invokeOn
import lk.kotlin.jvm.utils.async.thenOn
import lk.kotlin.okhttp.TypedResponse
import okhttp3.Request
import java.io.Closeable

/**
 * Loads images with a cache.
 * Created by joseph on 8/3/17.
 */
class ImageLoader(
        val baseRequest: Request.Builder = Request.Builder(),
        val imageMaxWidth: Int = 2048,
        val imageMaxHeight: Int = 2048,
        val maxCachedImages: Int = 100
) : Closeable {
    private val requests = HashSet<String>()
    private val callbacks = HashMap<String, ArrayList<(TypedResponse<Bitmap>) -> Unit>>()
    private val results = HashMap<String, Bitmap>()
    private val lastRequest = HashMap<String, Long>()

    fun getImage(context: Context, input: String, callback: (TypedResponse<Bitmap>) -> Unit) {
        lastRequest[input] = System.currentTimeMillis()
        if (results.containsKey(input)) {
            callback.invoke(TypedResponse(200, results[input]!!))
        } else {
            callbacks.getOrPut(input) { ArrayList() }.add(callback)
            if (!requests.contains(input)) {
                requests.add(input)

                val request: () -> TypedResponse<Bitmap> = if (input.startsWith("http")) {
                    baseRequest.url(input).lambdaBitmapExif(context, imageMaxWidth, imageMaxHeight)
                } else if (input.isNotEmpty()) {
                    {
                        try {
                            TypedResponse(
                                    200,
                                    context.getBitmapFromUri(Uri.parse(input), imageMaxWidth, imageMaxHeight)!!
                            )
                        } catch (e: Exception) {
                            TypedResponse<Bitmap>(0, exception = e)
                        }
                    }
                } else {
                    {
                        TypedResponse<Bitmap>(0, null)
                    }
                }

                request
                        .thenOn(UIThread) { out ->
                            requests.remove(input)
                            if (out.isSuccessful()) {
                                results[input] = out.result!!
                            }
                            clearCacheDownTo(maxCachedImages)
                            callbacks[input]?.forEach { it.invoke(out) }
                            callbacks.remove(input)
                        }.invokeOn(Async)
            }
        }
    }

    fun clearCacheDownTo(count: Int) {
        while (results.size > count) {
            val toRemove = lastRequest.minBy { it.value }!!
            lastRequest.remove(toRemove.key)
            results.remove(toRemove.key)
        }
    }

    override fun close() {
        for ((key, image) in results) {
            image.recycle()
        }
        results.clear()
    }
}