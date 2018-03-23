@file:JvmName("LkAndroidLifecycle")
@file:JvmMultifileClass

package lk.android.lifecycle




import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewGroup
import lk.kotlin.lifecycle.LifecycleConnectable
import lk.kotlin.lifecycle.LifecycleListener
import java.util.*

//View lifecycle stuff

private val View_lifecycleListener = WeakHashMap<View, ViewLifecycleListener>()

/**
 * A lifecycle for a view, that starts when the view is attached and ends when it is detatched.
 */
class ViewLifecycleListener(val view: View) : View.OnAttachStateChangeListener, LifecycleConnectable {

    var attached = ViewCompat.isAttachedToWindow(view)
        private set
    private val lifecycleListeners = ArrayList<LifecycleListener>()

    override fun onViewDetachedFromWindow(v: View?) {
        if (!attached) {
            println("Broken cycling detected in onViewDetachedFromWindow $view")
            return
        }
        lifecycleListeners.forEach { it.onStop() }
        attached = false
    }

    override fun onViewAttachedToWindow(v: View?) {
        if (attached) {
            println("Broken cycling detected in onViewAttachedToWindow $view")
            return
        }
        lifecycleListeners.forEach { it.onStart() }
        attached = true
    }

    override fun connect(listener: LifecycleListener) {
        if (attached) {
            listener.onStart()
        }
        lifecycleListeners.add(listener)
    }

    fun setAlwaysOn() {
        view.removeOnAttachStateChangeListener(this)
        if (!attached) {
            attached = true
            lifecycleListeners.forEach { it.onStart() }
        }
    }

    fun setAlwaysOnRecursive() {
        setAlwaysOn()
        view.forThisAndAllChildrenRecursive {
            View_lifecycleListener[it]?.setAlwaysOn()
        }
    }
}

private fun View.forThisAndAllChildrenRecursive(action: (View) -> Unit) {
    action.invoke(this)
    if (this is ViewGroup) {
        for (i in 0..this.childCount - 1) {
            getChildAt(i).forThisAndAllChildrenRecursive(action)
        }
    }
}

/**
 * Gets this view's lifecycle object for events to connect with.
 */
val View.lifecycle: ViewLifecycleListener
    get() = View_lifecycleListener.getOrPut(this) {
        val listener = ViewLifecycleListener(this)
        addOnAttachStateChangeListener(listener)
        listener
    }