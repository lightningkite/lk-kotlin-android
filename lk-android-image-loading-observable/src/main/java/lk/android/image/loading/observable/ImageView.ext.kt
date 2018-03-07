@file:JvmName("LkAndroidImageLoadingObservable")
@file:JvmMultifileClass

package lk.android.image.loading.observable



import android.net.Uri
import android.util.Log
import android.widget.ImageView
import lk.android.extensions.image.getBitmapFromUri
import lk.android.image.loading.image.ImageLoader
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import okhttp3.Request

enum class ImageViewBindUriState {
    STATE_BROKEN_IMAGE,
    STATE_NO_IMAGE,
    STATE_IMAGE
}

fun ImageView.bindUri(
        uriObservable: ObservableProperty<String?>,
        imageLoader: ImageLoader = ImageLoader(Request.Builder(), 2048, 2048),
        noImageResource: Int? = null,
        brokenImageResource: Int? = null,
        loadingObs: MutableObservableProperty<Boolean> = StandardObservableProperty(false),
        onLoadComplete: (state: ImageViewBindUriState) -> Unit = {}
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
            onLoadComplete(ImageViewBindUriState.STATE_NO_IMAGE)
        } else {
            val uriObj = Uri.parse(uri)
            if (uriObj.scheme.contains("http")) {
                loadingObs.value = (true)
                imageLoader.getImage(context, uri) {
                    //cancel if changed already
                    if (uri != uriObservable.value) return@getImage

                    loadingObs.value = (false)
                    if (it.result == null) {
                        //set to default image or broken image
                        if (brokenImageResource != null) {
                            setImageResource(brokenImageResource)
                        }
                        Log.e("ImageView.ext", "Error: " + it.errorString)
                        onLoadComplete(ImageViewBindUriState.STATE_BROKEN_IMAGE)
                    } else {
                        setImageBitmap(it.result)
                        onLoadComplete(ImageViewBindUriState.STATE_IMAGE)
                    }
                }
            } else {
                try {
                    setImageBitmap(context.getBitmapFromUri(Uri.parse(uri), 2048, 2048)!!)
                    onLoadComplete(ImageViewBindUriState.STATE_IMAGE)
                } catch (e: Exception) {
                    if (brokenImageResource != null) {
                        setImageResource(brokenImageResource)
                    }
                    Log.e("ImageView.ext", "Error: " + e.message)
                    onLoadComplete(ImageViewBindUriState.STATE_BROKEN_IMAGE)
                }
            }
        }
    }
}