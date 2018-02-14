@file:JvmName("Deprecated")
@file:JvmMultifileClass

package lk.everything.deprecated.anko.full

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import lk.android.extensions.image.getBitmapFromUri
import lk.android.image.loading.image.lambdaBitmapExif
import lk.android.lifecycle.lifecycle
import lk.android.ui.thread.UIThread
import lk.android.ui.thread.cancelling
import lk.everything.deprecated.async.invokeAsync
import lk.kotlin.jvm.utils.async.Async
import lk.kotlin.jvm.utils.async.invokeOn
import lk.kotlin.jvm.utils.async.thenOn
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import okhttp3.Request
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.imageResource


@Deprecated("Use the image loader version instead.")
fun ImageView.bindUri(
        uriObservable: ObservableProperty<String?>,
        noImageResource: Int? = null,
        brokenImageResource: Int? = null,
        imageMinBytes: Long,
        requestBuilder: Request.Builder = Request.Builder(),
        loadingObs: MutableObservableProperty<Boolean> = StandardObservableProperty(false),
        onLoadComplete: (state: Int) -> Unit = {}
) {
    var lastUri: String? = "nomatch"
    lifecycle.bind(uriObservable) { uri ->
        if (lastUri == uri) return@bind
        lastUri = uri

        if (uri == null || uri.isEmpty()) {
            //set to default image
            if (noImageResource != null) {
                imageResource = noImageResource
            }
            onLoadComplete(0)
        } else {
            val uriObj = Uri.parse(uri)
            if (uriObj.scheme.contains("http")) {
                loadingObs.value = (true)
                post {
                    requestBuilder.url(uri).lambdaBitmapExif(context, imageMinBytes).cancelling(this).thenOn(UIThread, {
                        if (it == null) return@thenOn
                        loadingObs.value = (false)
                        if (it.result == null) {
                            //set to default image or broken image
                            if (brokenImageResource != null) {
                                imageResource = brokenImageResource
                            }
                            Log.e("ImageView.ext", "Error: " + it.errorString)
                            onLoadComplete(-1)
                        } else {
                            imageBitmap = it.result
                            onLoadComplete(1)
                        }
                    }).invokeOn(Async)
                }
            } else {
                try {
                    imageBitmap = context.getBitmapFromUri(Uri.parse(uri), 2048, 2048)!!
                    onLoadComplete(1)
                } catch (e: Exception) {
                    if (brokenImageResource != null) {
                        imageResource = brokenImageResource
                    }
                    Log.e("ImageView.ext", "Error: " + e.message)
                    e.printStackTrace()
                    onLoadComplete(-1)
                }
            }
        }
    }
}

@Deprecated("Use the image loader version instead.")
fun ImageView.bindUri(
        uriObservable: ObservableProperty<String?>,
        noImageResource: Int? = null,
        brokenImageResource: Int? = null,
        imageMaxWidth: Int = 2048,
        imageMaxHeight: Int = 2048,
        requestBuilder: Request.Builder = Request.Builder(),
        loadingObs: MutableObservableProperty<Boolean> = StandardObservableProperty(false),
        onLoadComplete: (state: Int) -> Unit = {}
) {
    var lastUri: String? = "nomatch"
    lifecycle.bind(uriObservable) { uri ->
        if (lastUri == uri) return@bind
        lastUri = uri

        if (uri == null || uri.isEmpty()) {
            //set to default image
            if (noImageResource != null) {
                imageResource = noImageResource
            }
            onLoadComplete(0)
        } else {
            val uriObj = Uri.parse(uri)
            if (uriObj.scheme?.contains("http") == true) {
                loadingObs.value = (true)
                post {
                    requestBuilder.url(uri).lambdaBitmapExif(context, imageMaxWidth, imageMaxHeight).cancelling(this).invokeAsync {
                        if (it == null) {
                            return@invokeAsync
                        }
                        loadingObs.value = (false)
                        if (it.result == null) {
                            //set to default image or broken image
                            if (brokenImageResource != null) {
                                imageResource = brokenImageResource
                            }
                            Log.e("ImageView.ext", "Error: " + it.errorString)
                            onLoadComplete(-1)
                        } else {
                            imageBitmap = it.result
                            onLoadComplete(1)
                        }
                    }
                }
            } else {
                try {
                    imageBitmap = context.getBitmapFromUri(Uri.parse(uri), imageMaxWidth, imageMaxHeight)!!
                    onLoadComplete(1)
                } catch (e: Exception) {
                    if (brokenImageResource != null) {
                        imageResource = brokenImageResource
                    }
                    Log.e("ImageView.ext", "Error: " + e.message)
                    e.printStackTrace()
                    onLoadComplete(-1)
                }
            }
        }
    }
}

@Deprecated("Use the image loader version instead.")
fun ImageView.bindUri(
        uriObservable: ObservableProperty<String?>,
        cache: MutableMap<String, Bitmap>,
        noImageResource: Int? = null,
        brokenImageResource: Int? = null,
        imageMinBytes: Long,
        requestBuilder: Request.Builder = Request.Builder(),
        loadingObs: MutableObservableProperty<Boolean> = StandardObservableProperty(false),
        onLoadComplete: (state: Int) -> Unit = {}
) {
    var lastUri: String? = "nomatch"
    lifecycle.bind(uriObservable) { uri ->
        if (lastUri == uri) return@bind
        lastUri = uri

        if (uri == null || uri.isEmpty()) {
            //set to default image
            if (noImageResource != null) {
                imageResource = noImageResource
            }
            onLoadComplete(0)
        } else if (cache.containsKey(uri)) {
            imageBitmap = cache[uri]!!
            onLoadComplete(1)
        } else {
            val uriObj = Uri.parse(uri)
            if (uriObj.scheme.contains("http")) {
                loadingObs.value = (true)
                post {
                    requestBuilder.url(uri).lambdaBitmapExif(context, imageMinBytes).cancelling(this).invokeAsync {
                        if (it == null) return@invokeAsync
                        loadingObs.value = (false)
                        if (it.result == null) {
                            //set to default image or broken image
                            if (brokenImageResource != null) {
                                imageResource = brokenImageResource
                            }
                            Log.e("ImageView.ext", "Error: " + it.errorString)
                            onLoadComplete(-1)
                        } else {
                            cache.put(uri, it.result!!)
                            imageBitmap = it.result
                            onLoadComplete(1)
                        }
                    }
                }
            } else {
                try {
                    imageBitmap = context.getBitmapFromUri(Uri.parse(uri), 2048, 2048)!!
                    onLoadComplete(1)
                } catch (e: Exception) {
                    if (brokenImageResource != null) {
                        imageResource = brokenImageResource
                    }
                    onLoadComplete(-1)
                    Log.e("ImageView.ext", "Error: " + e.message)
                    e.printStackTrace()
                }
            }
        }
    }
}

@Deprecated("Use the image loader version instead.")
fun ImageView.bindUri(
        uriObservable: ObservableProperty<String?>,
        cache: MutableMap<String, Bitmap>,
        noImageResource: Int? = null,
        brokenImageResource: Int? = null,
        imageMaxWidth: Int = 2048,
        imageMaxHeight: Int = 2048,
        requestBuilder: Request.Builder = Request.Builder(),
        loadingObs: MutableObservableProperty<Boolean> = StandardObservableProperty(false),
        onLoadComplete: (state: Int) -> Unit = {}
) {
    var lastUri: String? = "nomatch"
    lifecycle.bind(uriObservable) { uri ->
        if (lastUri == uri) return@bind
        lastUri = uri

        if (uri == null || uri.isEmpty()) {
            //set to default image
            if (noImageResource != null) {
                imageResource = noImageResource
            }
            onLoadComplete(0)
        } else if (cache.containsKey(uri)) {
            imageBitmap = cache[uri]!!
            onLoadComplete(1)
        } else {
            val uriObj = Uri.parse(uri)
            if (uriObj.scheme.contains("http")) {
                loadingObs.value = (true)
                post {
                    requestBuilder.url(uri).lambdaBitmapExif(context, imageMaxWidth, imageMaxHeight).cancelling(this).invokeAsync {
                        if (it == null) return@invokeAsync
                        loadingObs.value = (false)
                        if (it.result == null) {
                            //set to default image or broken image
                            if (brokenImageResource != null) {
                                imageResource = brokenImageResource
                            }
                            Log.e("ImageView.ext", "Error: " + it.errorString)
                            onLoadComplete(-1)
                        } else {
                            cache.put(uri, it.result!!)
                            imageBitmap = it.result
                            onLoadComplete(1)
                        }
                    }
                }
            } else {
                try {
                    imageBitmap = context.getBitmapFromUri(Uri.parse(uri), 2048, 2048)!!
                    onLoadComplete(1)
                } catch (e: Exception) {
                    if (brokenImageResource != null) {
                        imageResource = brokenImageResource
                    }
                    Log.e("ImageView.ext", "Error: " + e.message)
                    onLoadComplete(-1)
                }
            }
        }
    }
}