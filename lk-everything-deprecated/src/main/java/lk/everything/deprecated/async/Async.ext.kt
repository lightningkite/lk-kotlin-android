@file:JvmName("Deprecated")

package lk.everything.deprecated.async

import lk.android.ui.thread.UIThread
import lk.kotlin.jvm.utils.async.Async
import lk.kotlin.jvm.utils.async.invokeFuture
import lk.kotlin.jvm.utils.async.invokeOn
import lk.kotlin.jvm.utils.async.thenOn
import java.util.*
import java.util.concurrent.*


@Deprecated("Use 'invokeOn' instead.", ReplaceWith(
        "invokeOn(Async)",
        "com.lightningkite.kotlin.async.Async",
        "com.lightningkite.kotlin.async.invokeOn"
))
fun <T> (() -> T).invokeAsync() = invokeOn(Async)

@Deprecated("Use 'invokeOn' instead.", ReplaceWith(
        "invokeFuture(Async)",
        "com.lightningkite.kotlin.async.Async",
        "com.lightningkite.kotlin.async.invokeOn"
))
fun <T> (() -> T).invokeAsyncFuture(): Future<T> = invokeFuture<T>(Async)

@Deprecated("Use 'invokeOn' instead.", ReplaceWith(
        "invokeOn(UIThread)",
        "com.lightningkite.kotlin.anko.async.UIThread",
        "com.lightningkite.kotlin.async.invokeOn"
))
fun (() -> Unit).invokeUIThread() = invokeOn(UIThread)

@Deprecated("Use 'invokeOn' instead.", ReplaceWith(
        "invokeOn(UIThread)",
        "com.lightningkite.kotlin.anko.async.UIThread",
        "com.lightningkite.kotlin.async.invokeOn"
))
@JvmName("invokeUIThreadT")
inline fun <T> (() -> T).invokeUIThread() = invokeOn(UIThread)

@Deprecated("Use 'invokeOn' instead.", ReplaceWith(
        "this.thenOn(UIThread, callback).invokeOn(Async)",
        "com.lightningkite.kotlin.async.Async",
        "com.lightningkite.kotlin.anko.async.UIThread",
        "com.lightningkite.kotlin.async.invokeOn",
        "com.lightningkite.kotlin.async.thenOn"
))
@JvmName("invokeUIThreadT")
fun <T> (() -> T).invokeAsync(callback: (T) -> Unit) = this.thenOn(UIThread, callback).invokeOn(Async)

/**
 * Runs [action] asynchronously.
 * @param action The lambda to run asynchronously.
 */
@Deprecated("Use 'Async.execute' instead.", ReplaceWith(
        "Async.execute(action)",
        "com.lightningkite.kotlin.async.Async",
        "com.lightningkite.kotlin.anko.async.UIThread",
        "com.lightningkite.kotlin.async.invokeOn",
        "com.lightningkite.kotlin.async.thenOn"
))
inline fun <T> doAsync(crossinline action: () -> T) = Async.execute { action() }

/**
 * Runs [action] asynchronously with its result being dealt with on the UI thread in [uiThread].
 * @param action The lambda to run asynchronously.
 * @param uiThread The lambda to run with the result of [action] on the UI thread.
 */
@Deprecated("Use 'Async.execute' instead.")
fun <T> doAsync(action: () -> T, uiThread: (T) -> Unit) {
    Async.execute({
        try {
            val result = action()
            UIThread.execute {
                uiThread(result)
            }
        } catch (e: Exception) {
            UIThread.execute {
                throw e
            }
        }
    })
}

/**
 * Posts [action] to the main thread.
 * @param action The lambda to run asynchronously.
 */
@Deprecated("Use 'UIThread.execute' instead.", ReplaceWith(
        "Async.execute(action)",
        "com.lightningkite.kotlin.anko.async.UIThread"
))
fun doUiThread(action: () -> Unit) = UIThread.execute(action)

@Deprecated("Place in your program, not the library.  Not used frequently enough for library status.")
fun <T> parallelNonblocking(tasks: List<() -> T>, onAllComplete: (List<T>) -> Unit) {
    if (tasks.isEmpty()) onAllComplete(listOf())
    val items = ArrayList<T>()
    for (task in tasks) {
        task.thenOn(UIThread) {
            items.add(it)
            if (items.size == tasks.size) {
                onAllComplete(items)
            }
        }.invokeOn(Async)
    }
}


//Weird async stuff below

@Deprecated("Place in your program, not the library.  Not used frequently enough for library status.")
inline fun <T> List<T>.withEachAsync(doTask: T.(() -> Unit) -> Unit, crossinline onAllComplete: () -> Unit) {
    if (isEmpty()) {
        onAllComplete()
        return
    }
    var itemsToGo = size
    for (item in this) {
        item.doTask {
            itemsToGo--
            if (itemsToGo <= 0) {
                onAllComplete()
            }
        }
    }
}

@Deprecated("Place in your program, not the library.  Not used frequently enough for library status.")
inline fun <T, MUTABLE, RESULT> List<T>.withReduceAsync(
        doTask: T.((RESULT) -> Unit) -> Unit,
        initialValue: MUTABLE,
        crossinline combine: MUTABLE.(RESULT) -> Unit,
        crossinline onAllComplete: (MUTABLE) -> Unit
) {
    if (isEmpty()) {
        onAllComplete(initialValue)
        return
    }
    var total = initialValue
    var itemsToGo = size
    for (item in this) {
        item.doTask {
            combine(total, it)
            itemsToGo--
            if (itemsToGo <= 0) {
                onAllComplete(total)
            }
        }
    }
}

@Deprecated("Place in your program, not the library.  Not used frequently enough for library status.")
inline fun parallelAsyncs(tasks: Collection<(() -> Unit) -> Unit>, crossinline onComplete: () -> Unit) {
    if (tasks.isEmpty()) {
        onComplete()
        return
    }
    var itemsToGo = tasks.size
    for (item in tasks) {
        item {
            itemsToGo--
            if (itemsToGo <= 0) {
                onComplete()
            }
        }
    }
}

@Deprecated("Place in your program, not the library.  Not used frequently enough for library status.")
inline fun parallelAsyncs(vararg tasks: (() -> Unit) -> Unit, crossinline onComplete: () -> Unit) {
    if (tasks.isEmpty()) {
        onComplete()
        return
    }
    var itemsToGo = tasks.size
    for (item in tasks) {
        item {
            itemsToGo--
            if (itemsToGo <= 0) {
                onComplete()
            }
        }
    }
}