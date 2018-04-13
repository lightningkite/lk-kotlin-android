@file:JvmName("LkAndroidExtensions")
@file:JvmMultifileClass

package lk.android.extensions.files



import android.content.ContentResolver
import android.net.Uri

/**
 * Attempts to find and return the size of the file at the given [uri].
 */
fun ContentResolver.fileSize(uri: Uri): Long? {
    return openFileDescriptor(uri, "r")?.statSize
}