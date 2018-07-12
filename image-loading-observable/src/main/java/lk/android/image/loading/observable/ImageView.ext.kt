@file:JvmName("LkAndroidImageLoadingObservable")
@file:JvmMultifileClass

package lk.android.image.loading.observable


import android.net.Uri
import android.util.Log
import android.widget.ImageView
import lk.android.image.loading.image.ImageLoader
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import lk.kotlin.observable.property.transform
import okhttp3.Request

enum class ImageViewBindUriState {
    STATE_BROKEN_IMAGE,
    STATE_NO_IMAGE,
    STATE_IMAGE
}

@JvmName("bindUri")
fun ImageView.bindUri(
        uriObservable: ObservableProperty<Uri?>,
        imageLoader: ImageLoader = ImageLoader(Request.Builder(), 2048, 2048),
        noImageResource: Int? = null,
        brokenImageResource: Int? = null,
        loadingObs: MutableObservableProperty<Boolean> = StandardObservableProperty(false),
        onLoadComplete: (state: ImageViewBindUriState) -> Unit = {}
) = bindUri(
        uriObservable = uriObservable.transform { it?.toString() },
        imageLoader = imageLoader,
        noImageResource = noImageResource,
        brokenImageResource = brokenImageResource,
        loadingObs = loadingObs,
        onLoadComplete = onLoadComplete
)

@JvmName("bindUriString")
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

        if (noImageResource != null) {
            setImageResource(noImageResource)
        } else setImageDrawable(null)

        if (uri == null || uri.isEmpty()) {
            loadingObs.value = false
            //set to default image
            if (noImageResource != null) {
                setImageResource(noImageResource)
            }
            onLoadComplete(ImageViewBindUriState.STATE_NO_IMAGE)
        } else {
            loadingObs.value = true
            imageLoader.getImage(context, uri) {
                //cancel if changed already
                if (uri != uriObservable.value) return@getImage

                loadingObs.value = false
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
        }
    }
}