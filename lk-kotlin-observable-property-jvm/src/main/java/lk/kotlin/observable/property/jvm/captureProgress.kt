@file:JvmName("LkKotlinObservablePropertyJvm")
@file:JvmMultifileClass

package lk.kotlin.observable.property.jvm


import lk.kotlin.observable.property.MutableObservableProperty
import java.util.concurrent.Executor

/**
 * Capture whether or not the lambda is running.
 * Created by joseph on 1/31/18.
 */

fun <T> (() -> T).captureProgress(observable: MutableObservableProperty<Boolean>, executor: Executor): (() -> T) {
    return {
        executor.execute {
            observable.value = true
        }
        val result = this()
        executor.execute {
            observable.value = false
        }
        result
    }
}

@JvmName("attachLoadingObservableInt")
fun <T> (() -> T).captureProgress(observable: MutableObservableProperty<Int>, executor: Executor): (() -> T) {
    return {
        executor.execute {
            observable.value++
        }
        val result = this()
        executor.execute {
            observable.value--
        }
        result
    }
}