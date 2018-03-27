package lk.kotlin.android.example.activity.access

import android.app.Activity
import android.net.Uri
import android.os.Environment
import android.view.Gravity
import android.view.View
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import lk.android.activity.access.ActivityAccess
import lk.android.activity.access.intentCamera
import lk.android.activity.access.intentGallery
import lk.android.activity.access.startIntent
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.extensions.anko
import lk.kotlin.jvm.utils.files.child
import lk.kotlin.lifecycle.listen
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*
import java.text.DateFormat
import java.util.*

/**
 * This example is meant to demonstrate how to use ActivityAccess's special features.
 */
class ActivityAccessVG() : ViewGenerator {

    val lastPaused = StandardObservableProperty<Date?>(null)
    val lastResumed = StandardObservableProperty<Date?>(null)
    val imageUri = StandardObservableProperty<Uri?>(null)

    override fun invoke(access: ActivityAccess): View = access.context.anko().scrollView {

        isFillViewport = true

        verticalLayout {
            gravity = Gravity.CENTER
            padding = dip(8)

            //Listening to onPause in the activity, but only while this view is around.
            lifecycle.listen(access.onPause) {
                lastPaused.value = Date()
            }

            //Listening to onPause in the activity, but only while this view is around.
            lifecycle.listen(access.onResume) {
                lastResumed.value = Date()
            }

            textView {
                lifecycle.bind(lastPaused) {
                    val asString = it?.let { DateFormat.getTimeInstance().format(it) }
                    text = "This view was last paused at $asString"
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }

            textView {
                lifecycle.bind(lastResumed) {
                    val asString = it?.let { DateFormat.getTimeInstance().format(it) }
                    text = "This view was last resumed at $asString"
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }

            textView {
                lifecycle.bind(imageUri) {
                    text = "Latest URI selected: $it"
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }

            button {
                text = "Select image from Gallery"
                setOnClickListener {
                    //Request an image from the gallery
                    access.intentGallery {
                        imageUri.value = it
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
                        imageUri.value = it
                    }
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }

            button {
                text = "Select image from Camera (Public)"
                setOnClickListener {
                    //We're requesting permission to use external storage first.
                    access.requestPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) { granted ->
                        if (granted) {
                            val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).child("LKExample")
                            //Request an image from the camera.
                            access.intentCamera(folder = folder) {
                                imageUri.value = it
                            }
                        }
                    }
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }

            button {
                text = "Crop image using external library"
                setOnClickListener {
                    val uri = imageUri.value ?: return@setOnClickListener
                    val intent = CropImage.activity(uri)
                            .setRequestedSize(1024, 1024, CropImageView.RequestSizeOptions.SAMPLING)
                            .getIntent(context)

                    //We can use another intent with the lambda callback system.
                    access.startIntent(intent) { resultCode, data ->
                        if (resultCode == Activity.RESULT_OK) {
                            imageUri.value = CropImage.getActivityResult(data).uri
                        }
                    }
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }

            button {
                text = "Crop image using external library (2)"
                setOnClickListener {
                    val uri = imageUri.value ?: return@setOnClickListener

                    //If we have to use a library that calls startForResult for us, or even forces us to use a certain request code, we can use this:
                    val forcedRequestCode = 432
                    access.prepareOnResult(forcedRequestCode) { resultCode, data ->
                        if (resultCode == Activity.RESULT_OK) {
                            imageUri.value = CropImage.getActivityResult(data).uri
                        }
                    }

                    access.activity!!.startActivityForResult(
                            CropImage.activity(uri)
                                    .setRequestedSize(1024, 1024, CropImageView.RequestSizeOptions.SAMPLING)
                                    .getIntent(context),
                            forcedRequestCode
                    )
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }
        }.lparams(matchParent, wrapContent)
    }
}