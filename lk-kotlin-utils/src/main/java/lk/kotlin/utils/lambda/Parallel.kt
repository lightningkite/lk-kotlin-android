package lk.kotlin.utils.lambda

import java.util.ArrayList
import java.util.concurrent.FutureTask
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

fun <A, B> parallel(a: () -> A, b: () -> B): () -> Pair<A, B> {
    return {
        val futureA = FutureTask {
            a.invoke()
        }
        val futureB = FutureTask {
            b.invoke()
        }
        Thread(futureA).start()
        Thread(futureB).start()
        futureA.get() to futureB.get()
    }
}

fun <A, B, C> parallel(a: () -> A, b: () -> B, c: () -> C): () -> Triple<A, B, C> {
    return {
        val futureA = FutureTask {
            a.invoke()
        }
        val futureB = FutureTask {
            b.invoke()
        }
        val futureC = FutureTask {
            c.invoke()
        }
        Thread(futureA).start()
        Thread(futureB).start()
        Thread(futureC).start()
        Triple(futureA.get(), futureB.get(), futureC.get())
    }
}

@JvmName("parallelBlockingShorthand")
fun <T> List<() -> T>.parallel(): () -> List<T> = parallel(this)

fun <T> parallel(tasks: List<() -> T>): () -> List<T> {
    val numCores = Runtime.getRuntime().availableProcessors()
    if (tasks.isEmpty()) return { listOf() }
    else if (tasks.size < numCores) {
        return {
            try {
                val results = tasks.subList(0, tasks.size - 1).map {
                    val future = FutureTask {
                        it.invoke()
                    }
                    Thread(future) to future
                }.map { it.first.start(); it.second }.map { it.get() }.toMutableList()
                results += tasks.last().invoke()
                results
            } catch (e: Exception) {
                e.printStackTrace()
                tasks.map { it() }
            }
        }
    } else {
        return {
            val pool = ThreadPoolExecutor(
                    Runtime.getRuntime().availableProcessors(),
                    Runtime.getRuntime().availableProcessors(),
                    1,
                    TimeUnit.SECONDS,
                    LinkedBlockingQueue<Runnable>()
            )
            try {
                val results = tasks.subList(0, tasks.size - 1).map {
                    pool.submit(it)
                }.map { it.get() }.toMutableList()
                results += tasks.last().invoke()
                results
            } catch (e: Exception) {
                e.printStackTrace()
                tasks.map { it() }
            }
        }
    }
}