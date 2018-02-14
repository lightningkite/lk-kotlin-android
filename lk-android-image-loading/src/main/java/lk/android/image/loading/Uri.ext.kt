@file:JvmName("LkAndroidImageLoading")
@file:JvmMultifileClass

package lk.android.image.loading



import android.content.ContentResolver
import android.net.Uri
import lk.kotlin.jvm.utils.stream.toByteArray
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Converts a [Uri] into a [RequestBody] using a content [resolver].
 */
fun Uri.toRequestBody(resolver: ContentResolver): RequestBody {
    val type = resolver.getType(this) ?: pathSegments.lastOrNull()?.split('.')?.lastOrNull()
    ?: throw IllegalArgumentException()
    return okhttp3.RequestBody.create(MediaType.parse(type), resolver.openInputStream(this).toByteArray())
}