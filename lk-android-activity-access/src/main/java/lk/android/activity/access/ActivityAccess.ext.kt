@file:JvmName("LkAndroidActivityAccess")
@file:JvmMultifileClass

package lk.android.activity.access



import android.content.Intent
import android.os.Bundle

/**
 * Starts an intent with a direct callback.
 */
fun ActivityAccess.startIntent(intent: Intent, options: Bundle = Bundle(), onResult: (Int, Intent?) -> Unit = { _, _ -> }) {
    activity?.startActivityForResult(intent, prepareOnResult(onResult = onResult), options)
}