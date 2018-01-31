@file:JvmName("LkAndroidImageLoadingObservable")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko.full

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.lightningkite.kotlin.anko.image.getBitmapFromUri
import com.lightningkite.kotlin.anko.lifecycle
import com.lightningkite.kotlin.anko.networking.image.ImageLoader
import com.lightningkite.kotlin.observable.property.MutableObservableProperty
import com.lightningkite.kotlin.observable.property.ObservableProperty
import com.lightningkite.kotlin.observable.property.StandardObservableProperty
import com.lightningkite.kotlin.observable.property.bind
import okhttp3.Request

fun ImageView.bindUri(
        uriObservable: ObservableProperty<String?>,
        imageLoader: ImageLoader = ImageLoader(Request.Builder(), 2048, 2048),
        noImageResource: Int? = null,
        brokenImageResource: Int? = null,
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
                setImageResource(noImageResource)
            }
            onLoadComplete(0)
        } else {
            val uriObj = Uri.parse(uri)
            if (uriObj.scheme.contains("http")) {
                loadingObs.value = (true)
                imageLoader.getImage(context, uri) {
                    loadingObs.value = (false)
                    if (it.result == null) {
                        //set to default image or broken image
                        if (brokenImageResource != null) {
                            setImageResource(brokenImageResource)
                        }
                        Log.e("ImageView.ext", "Error: " + it.errorString)
                        onLoadComplete(-1)
                    } else {
                        setImageBitmap(it.result)
                        onLoadComplete(1)
                    }
                }
            } else {
                try {
                    setImageBitmap(context.getBitmapFromUri(Uri.parse(uri), 2048, 2048)!!)
                    onLoadComplete(1)
                } catch (e: Exception) {
                    if (brokenImageResource != null) {
                        setImageResource(brokenImageResource)
                    }
                    Log.e("ImageView.ext", "Error: " + e.message)
                    onLoadComplete(-1)
                }
            }
        }
    }
}