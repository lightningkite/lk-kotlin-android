package lk.anko.extensions

import android.content.Context
import org.jetbrains.anko.AnkoContextImpl


/**
 * Creates an Anko context.
 */
fun Context.anko(): AnkoContextImpl<Context> {
    return AnkoContextImpl(this, this, false)
}

fun <T> Context.anko(owner: T): AnkoContextImpl<T> {
    return AnkoContextImpl(this, owner, false)
}