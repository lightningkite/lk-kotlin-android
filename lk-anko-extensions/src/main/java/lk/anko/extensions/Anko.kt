package lk.anko.extensions

import android.content.Context
import android.view.View
import org.jetbrains.anko.AnkoContextImpl


/**
 * Creates a view using Anko.
 */
inline fun <T> Context.anko(owner: T, setup: AnkoContextImpl<T>.() -> Unit): View {
    return AnkoContextImpl<T>(this, owner, false).apply(setup).view
}

/**
 * Creates a view using Anko.
 */
inline fun Context.anko(setup: AnkoContextImpl<Context>.() -> Unit): View {
    return AnkoContextImpl(this, this, false).apply(setup).view
}

/**
 * Creates an Anko context.
 */
fun <T> Context.anko(owner: T): AnkoContextImpl<T> {
    return AnkoContextImpl<T>(this, owner, false)
}

/**
 * Creates an Anko context.
 */
fun Context.anko(): AnkoContextImpl<Context> {
    return AnkoContextImpl(this, this, false)
}