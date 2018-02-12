package com.lightningkite.kotlin.anko.viewcontrollers.image

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import com.lightningkite.kotlin.anko.image.getBitmapFromUri
import com.lightningkite.kotlin.anko.selector
import com.lightningkite.kotlin.anko.viewcontrollers.VCContext
import com.lightningkite.kotlin.anko.viewcontrollers.startIntent
import com.lightningkite.kotlin.files.child
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Opens a dialog requesting an image from either the camera or the gallery.
 */
fun VCContext.dialogPublicImageUri(fileProviderAuthority: String, publicFolderName: String?, cameraRes: Int, galleryRes: Int, onResult: (Uri?) -> Unit) {
    activity?.selector(
            null,
            cameraRes to {
                requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    getPublicImageUriFromCamera(fileProviderAuthority, publicFolderName) {
                        Log.i("ImageUploadLayout", it.toString())
                        onResult(it)
                    }
                }
            },
            galleryRes to {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    getImageUriFromGallery {
                        Log.i("ImageUploadLayout", it.toString())
                        onResult(it)
                    }
                }
            }
    )
}

fun VCContext.dialogImageUri(fileProviderAuthority: String, cameraRes: Int, galleryRes: Int, onResult: (Uri?) -> Unit) {
    activity?.selector(
            null,
            cameraRes to {
                requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    getImageUriFromCamera(fileProviderAuthority = fileProviderAuthority) {
                        Log.i("ImageUploadLayout", it.toString())
                        onResult(it)
                    }
                }
            },
            galleryRes to {
                requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE) {
                    getImageUriFromGallery() {
                        Log.i("ImageUploadLayout", it.toString())
                        onResult(it)
                    }
                }
            }
    )
}

/**
 * Opens a dialog requesting an image from either the camera or the gallery.
 */
fun VCContext.dialogImage(fileProviderAuthority: String, minBytes: Long, cameraRes: Int, galleryRes: Int, onResult: (Bitmap?) -> Unit) {
    activity?.selector(
            null,
            cameraRes to {
                requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    getImageFromCamera(fileProviderAuthority, minBytes) {
                        onResult(it)
                    }
                }
            },
            galleryRes to {
                requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE) {
                    getImageFromGallery(minBytes) {
                        onResult(it)
                    }
                }
            }
    )
}

/**
 * Pops up a dialog for getting an image from the gallery, returning it in [onResult].
 */
fun VCContext.getImageUriFromGallery(onResult: (Uri?) -> Unit) {
    val getIntent = Intent(Intent.ACTION_GET_CONTENT)
    getIntent.type = "image/*"

    val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    pickIntent.type = "image/*"

    val chooserIntent = Intent.createChooser(getIntent, "Select Image")
    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

    startIntent(chooserIntent) { code, data ->
        if (code != Activity.RESULT_OK) {
            onResult(null); return@startIntent
        }
        if (data == null) return@startIntent
        val imageUri = data.data
        onResult(imageUri)
    }
}

/**
 * Opens the camera to take a picture, returning it in [onResult].
 */
fun VCContext.getImageUriFromCamera(fileProviderAuthority: String, onResult: (Uri?) -> Unit) {
    try {
        val folder = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        folder.mkdir()

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val file = File.createTempFile(timeStamp, ".jpg", folder)
        val potentialFile: Uri = FileProvider.getUriForFile(context, fileProviderAuthority, file)

        getImageUriFromCamera(potentialFile, onResult)
    } catch (e: Exception) {
        e.printStackTrace()
        onResult(null)
    }
}

/**
 * Opens the camera to take a picture, returning it in [onResult].
 */
fun VCContext.getPublicImageUriFromCamera(fileProviderAuthority: String, publicFolderName: String? = null, onResult: (Uri?) -> Unit) {
    try {
        val publicPictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val folder = if (publicFolderName == null) publicPictures else publicPictures.child(publicFolderName)

        folder.mkdir()

        val timeStamp = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Date())
        val file = folder.child(timeStamp + ".jpg")

        val potentialFile = FileProvider.getUriForFile(context, fileProviderAuthority, file)

        getImageUriFromCamera(potentialFile, onResult)
    } catch (e: Exception) {
        e.printStackTrace()
        onResult(null)
    }
}

/**
 * Opens the camera to take a picture, returning it in [onResult].
 */
fun VCContext.getImageUriFromCamera(requestedUri: Uri, onResult: (Uri?) -> Unit) {
    try {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, requestedUri)
        this.startIntent(intent) { code, data ->
            if (code != Activity.RESULT_OK) {
                onResult(null); return@startIntent
            }
            println("Actual data:" + data?.data?.toString())
            //val fixedUri = Uri.fromFile(File((data?.data ?: potentialFile).getRealPath(this)))
//            val fixedUri = File((data?.data ?: potentialFile).getRealPath(this)).toImageContentUri(this)

            onResult(data?.data ?: requestedUri)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        onResult(null)
    }
}

/**
 * Pops up a dialog for getting an image from the gallery, returning it in [onResult].
 */
fun VCContext.getImageFromGallery(minBytes: Long, onResult: (Bitmap?) -> Unit) {
    val getIntent = Intent(Intent.ACTION_GET_CONTENT)
    getIntent.type = "image/*"

    val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    pickIntent.type = "image/*"

    val chooserIntent = Intent.createChooser(getIntent, "Select Image")
    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

    this.startIntent(chooserIntent) { code, data ->
        if (code != Activity.RESULT_OK) {
            onResult(null); return@startIntent
        }
        if (data == null) return@startIntent
        val imageUri = data.data
        onResult(context.getBitmapFromUri(imageUri, minBytes))
    }
}

/**
 * Opens the camera to take a picture, returning it in [onResult].
 */
fun VCContext.getImageFromCamera(fileProviderAuthority: String, minBytes: Long, onResult: (Bitmap?) -> Unit) {
    val folder = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    if (folder == null) {
        onResult(null)
        return;
    }

    folder.mkdir()

    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val file = File(folder, "image_" + timeStamp + "_raw.jpg")
    val potentialFile = FileProvider.getUriForFile(context, fileProviderAuthority, file)

    intent.putExtra(MediaStore.EXTRA_OUTPUT, potentialFile)
    this.startIntent(intent) { code, data ->
        if (code != Activity.RESULT_OK) {
            onResult(null); return@startIntent
        }
        onResult(context.getBitmapFromUri(potentialFile, minBytes))
    }
}