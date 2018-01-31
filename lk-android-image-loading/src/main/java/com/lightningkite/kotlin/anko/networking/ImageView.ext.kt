@file:JvmName("LkAndroidImageLoading")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko.networking

import android.net.Uri
import android.widget.ImageView
import com.lightningkite.kotlin.anko.async.UIThread
import com.lightningkite.kotlin.anko.image.getBitmapFromUri
import com.lightningkite.kotlin.anko.networking.image.lambdaBitmapExif
import com.lightningkite.kotlin.async.Async
import com.lightningkite.kotlin.async.invokeOn
import com.lightningkite.kotlin.async.thenOn
import okhttp3.Request

/**
 * Sets an [ImageView] to display pretty much any type of URI.
 */
fun ImageView.imageAnyUri(
        uri: String?,
        noImageResource: Int? = null,
        brokenImageResource: Int? = null,
        imageMinBytes: Long,
        requestBuilder: Request.Builder = Request.Builder(),
        onComplete: (Boolean) -> Unit
) {
    if (uri == null || uri.isEmpty()) {
        //set to default image
        if (noImageResource != null) {
            setImageResource(noImageResource)
        }
        onComplete(false)
    } else {
        val uriObj = Uri.parse(uri)
        if (uriObj.scheme.contains("http")) {
            requestBuilder.url(uri).lambdaBitmapExif(context, imageMinBytes).thenOn(UIThread) {
                if (it.result == null) {
                    //set to default image or broken image
                    if (brokenImageResource != null) {
                        setImageResource(brokenImageResource)
                    }
                    onComplete(false)
                } else {
                    setImageBitmap(it.result)
                    onComplete(true)
                }
            }.invokeOn(Async)
        } else {
            try {
                setImageBitmap(context.getBitmapFromUri(Uri.parse(uri), 2048, 2048)!!)
                onComplete(true)
            } catch (e: Exception) {
                if (brokenImageResource != null) {
                    setImageResource(brokenImageResource)
                }
                onComplete(false)
            }
        }
    }
}