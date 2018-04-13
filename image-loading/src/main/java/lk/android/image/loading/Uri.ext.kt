@file:JvmName("LkAndroidImageLoading")
@file:JvmMultifileClass

package lk.android.image.loading



import android.content.ContentResolver
import android.net.Uri
import lk.kotlin.jvm.utils.stream.toByteArray
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Converts a [uri] into a [RequestBody].
 */
fun ContentResolver.toRequestBody(uri: Uri): RequestBody {
    val type = this.getType(uri) ?: uri.pathSegments.lastOrNull()?.split('.')?.lastOrNull()
    ?: throw IllegalArgumentException()
    //TODO: This can be optimized for streaming
    return okhttp3.RequestBody.create(MediaType.parse(type), this.openInputStream(uri).toByteArray())
}