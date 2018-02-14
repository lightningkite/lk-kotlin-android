@file:JvmName("LkKotlinObservableListLifecycle")
@file:JvmMultifileClass

package lk.kotlin.observable.list.lifecycle

import lk.kotlin.lifecycle.LifecycleConnectable
import lk.kotlin.lifecycle.LifecycleListener
import lk.kotlin.observable.list.ObservableList
import lk.kotlin.observable.list.ObservableListListenerSet
import lk.kotlin.observable.list.addListenerSet
import lk.kotlin.observable.list.removeListenerSet
import lk.kotlin.observable.property.lifecycle.bind


/**
 * Extensions that allow using ObservablePropertys with the LifecycleConnectable.
 * Created by jivie on 6/1/16.
 */

fun <T> LifecycleConnectable.bind(observable: ObservableList<T>, listener: (ObservableList<T>) -> Unit) {
    bind(observable.onUpdate, listener)
}

fun <T> LifecycleConnectable.bind(observable: ObservableList<T>, listenerSet: ObservableListListenerSet<T>) {
    connect(object : LifecycleListener {
        override fun onStart() {
            observable.addListenerSet(listenerSet)
        }

        override fun onStop() {
            observable.removeListenerSet(listenerSet)
        }
    })
}