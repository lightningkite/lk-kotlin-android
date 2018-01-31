@file:JvmName("LkKotlinObservableProperty")
@file:JvmMultifileClass

package com.lightningkite.kotlin.observable.property

operator fun <T> ObservableProperty<T>.plusAssign(lambda: (T) -> Unit): Unit {
    add(lambda)
}

operator fun <T> ObservableProperty<T>.minusAssign(lambda: (T) -> Unit): Unit {
    remove(lambda)
}

fun <A> ObservableProperty<A>.addAndInvoke(lambda: (A) -> Unit) {
    add(lambda)
    lambda.invoke(value)
}