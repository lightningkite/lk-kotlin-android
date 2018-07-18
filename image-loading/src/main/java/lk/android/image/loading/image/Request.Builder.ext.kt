@file:JvmName("LkAndroidImageLoading")
@file:JvmMultifileClass

package lk.android.image.loading.image



import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import lk.android.extensions.image.getBitmapFromUri
import lk.kotlin.jvm.utils.stream.toFile
import lk.kotlin.okhttp.lambda
import okhttp3.Request
import java.io.File

/**
 * Converts the request into a lambda that returns a bitmap.
 */
fun Request.Builder.lambdaBitmap(options: BitmapFactory.Options = BitmapFactory.Options()) = lambda<Bitmap> {
    BitmapFactory.decodeStream(it.body()!!.byteStream(), null, options)
}

/**
 * Converts the request into a lambda that returns a bitmap.
 * Writes the file into the cache and loads it, which takes care of EXIF rotation issues.
 */
fun Request.Builder.lambdaBitmapExif(context: Context, maxWidth: Int = 2048, maxHeight: Int = 2048) = lambda<Bitmap> {
    val tempFile = File.createTempFile("image", "jpg", context.cacheDir)
    it.body()!!.byteStream()!!.toFile(tempFile)
    context.getBitmapFromUri(Uri.fromFile(tempFile), maxWidth, maxHeight)!!
}