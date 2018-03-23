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

fun Request.Builder.lambdaBitmap(options: BitmapFactory.Options = BitmapFactory.Options()) = lambda<Bitmap> {
    BitmapFactory.decodeStream(it.body()!!.byteStream(), null, options)
}

fun Request.Builder.lambdaBitmapExif(context: Context, maxWidth: Int = 2048, maxHeight: Int = 2048) = lambda<Bitmap> {
    val tempFile = File.createTempFile("image", "jpg", context.cacheDir)
    it.body()!!.byteStream()!!.toFile(tempFile)
    context.getBitmapFromUri(Uri.fromFile(tempFile), maxWidth, maxHeight)!!
}