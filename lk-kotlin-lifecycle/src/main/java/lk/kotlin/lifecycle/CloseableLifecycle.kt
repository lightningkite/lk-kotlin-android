package lk.kotlin.lifecycle


import java.io.Closeable
import java.util.*

/**
 * A lifecycle that starts immediately and stops upon closing.
 * Created by jivie on 6/28/16.
 */
class CloseableLifecycle() : LifecycleConnectable, Closeable {
    val toRemove = ArrayList<LifecycleListener>()
    override fun connect(listener: LifecycleListener) {
        listener.onStart()
        toRemove.add(listener)
    }

    override fun close() {
        toRemove.forEach { it.onStop() }
        toRemove.clear()
    }

}