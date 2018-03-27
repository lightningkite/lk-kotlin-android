package lk.kotlin.android.example.image.loading.observable

import android.net.Uri
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.activity.access.intentCamera
import lk.android.activity.access.intentGallery
import lk.android.image.loading.observable.bindUri
import lk.android.mighty.view.ViewGenerator
import lk.anko.extensions.anko
import lk.kotlin.observable.property.StandardObservableProperty
import org.jetbrains.anko.*

class ImageLoadingVG() : ViewGenerator {

    val uriObs = StandardObservableProperty<Uri?>(null)

    override fun invoke(access: ActivityAccess): View = access.context.anko().scrollView {

        isFillViewport = true

        verticalLayout {

            gravity = Gravity.CENTER

            imageView {
                bindUri(uriObs)
            }.lparams(matchParent, dip(300))

            button {
                text = "Select image from Gallery"
                setOnClickListener {
                    //Request an image from the gallery
                    access.intentGallery {
                        uriObs.value = it
                    }
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }

            button {
                text = "Select image from Camera (Temporary)"
                setOnClickListener {
                    //Request an image from the camera.
                    /*
                    Note that this is using the default file provider and path, which REQUIRE
                    the file provider to be in the manifest at '<app package name>.fileprovider'
                    and the file provider have an entry for the cache path "images".
                    */
                    access.intentCamera {
                        uriObs.value = it
                    }
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }

        }.lparams(matchParent, wrapContent)
    }
}