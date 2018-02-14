@file:JvmName("Deprecated")
@file:JvmMultifileClass

package lk.everything.deprecated.networking

import lk.android.ui.thread.UIThread
import lk.kotlin.okhttp.TypedResponse
import lk.kotlin.okhttp.thenOnFailure
import lk.kotlin.okhttp.thenOnSuccess
import lk.kotlin.okhttp.transformResult

@Deprecated("Use 'captureSuccess' with executor parameter instead.", ReplaceWith(
        "captureSuccess(UIThread, onFailure)",
        "com.lightningkite.kotlin.anko.async.UIThread"
))
inline fun <T> (() -> TypedResponse<T>).captureSuccess(crossinline onSuccess: (T) -> Unit): () -> TypedResponse<T> = thenOnSuccess(UIThread, onSuccess)

@Deprecated("Use 'captureFailure' with executor parameter instead.", ReplaceWith(
        "captureFailure(UIThread, onFailure)",
        "com.lightningkite.kotlin.anko.async.UIThread"
))
inline fun <T> (() -> TypedResponse<T>).captureFailure(crossinline onFailure: (TypedResponse<T>) -> Unit): () -> TypedResponse<T> = thenOnFailure(UIThread, onFailure)


@Deprecated("Use 'transformResult' instead.", ReplaceWith(
        "transformResult(mapper)",
        "com.lightningkite.kotlin.networking.transformResult"
))
inline fun <A, B> (() -> TypedResponse<A>).mapResult(crossinline mapper: (A) -> B): () -> TypedResponse<B> = transformResult(mapper)
