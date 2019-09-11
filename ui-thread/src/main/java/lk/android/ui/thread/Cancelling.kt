package lk.android.ui.thread



import androidx.core.view.ViewCompat
import android.view.View

/**
 * Cancels the lambda if the view is not attached.
 * Created by jivie on 1/22/16.
 */
fun <T> (() -> T).cancelling(view: View, default: T): () -> T {
    return {
        if (!ViewCompat.isAttachedToWindow(view)) default
        else this.invoke()
    }
}

/**
 * Cancels the lambda if the view is not attached.
 * Created by jivie on 1/22/16.
 */
fun <T> (() -> T).cancelling(view: View): () -> T? {
    return {
        if (!ViewCompat.isAttachedToWindow(view)) null
        else this.invoke()
    }
}