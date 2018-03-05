package lk.android.activity.access

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun ActivityAccess.intentCameraRaw(
        requestedUri: Uri,
        callback : (Uri?)->Unit
) {
    requestPermission(Manifest.permission.CAMERA) {
        if(it){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, requestedUri)
            startIntent(intent){ code, result ->
                if(code == Activity.RESULT_OK) callback(result?.data ?: requestedUri)
                else callback(null)
            }
        } else {
            callback(null)
        }
    }
}

fun ActivityAccess.intentCameraTemp(
        fileProviderAuthority:String,
        callback : (Uri?)->Unit
) {

    val file = context.cacheDir
        .let { File.createTempFile("", ".jpg", it) }
        .let { FileProvider.getUriForFile(context, fileProviderAuthority, it) }
    intentCameraRaw(file, callback)
}

fun ActivityAccess.intentCameraPublic(
        fileProviderAuthority:String,
        publicFolderName:String,
        callback : (Uri?)->Unit
) {
    requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)) {
        if(it.values.all { it == PackageManager.PERMISSION_GRANTED }) {
            val publicPictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val folder = publicPictures.let { File(it, publicFolderName) }

            folder.mkdir()

            val timeStamp = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Date())
            val file = folder.let { File(it, timeStamp + ".jpg") }
            val potentialFile = FileProvider.getUriForFile(context, fileProviderAuthority, file)

            intentCameraRaw(potentialFile, callback)
        }
    }
}

fun ActivityAccess.intentGallery(
        callback: (Uri?) -> Unit
){
    requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE){
        if(it){
            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"

            val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickIntent.type = "image/*"

            val chooserIntent = Intent.createChooser(getIntent, "Select Image")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

            this.startIntent(chooserIntent) { code, result ->
                if(code == Activity.RESULT_OK) callback(result?.data)
                else callback(null)
            }
        } else {
            callback(null)
        }
    }
}