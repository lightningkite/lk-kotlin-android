@file:JvmName("LkAndroidLifecycle")
@file:JvmMultifileClass

package lk.android.lifecycle




import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewGroup
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.lifecycle.Lifecycle
import lk.kotlin.utils.lambda.invokeAll
import java.util.*

//View lifecycle stuff

private val View_lifecycleListener = WeakHashMap<View, ViewLifecycleListener>()

/**
 * A lifecycle for a view, that starts when the view is attached and ends when it is detatched.
 */
class ViewLifecycleListener(val view: View) : View.OnAttachStateChangeListener, Lifecycle {

    override var value = ViewCompat.isAttachedToWindow(view)
        private set

    @Transient
    val listeners = ArrayList<(Boolean) -> Unit>()

    override fun add(element: (Boolean) -> Unit): Boolean = listeners.add(element)
    override fun remove(element: (Boolean) -> Unit): Boolean = listeners.remove(element)

    override fun onViewDetachedFromWindow(v: View?) {
        if (!value) {
            println("Broken cycling detected in onViewDetachedFromWindow $view")
            return
        }
        value = false
        listeners.invokeAll(value)
    }

    override fun onViewAttachedToWindow(v: View?) {
        if (value) {
            println("Broken cycling detected in onViewAttachedToWindow $view")
            return
        }
        value = true
        listeners.invokeAll(value)
    }

    fun setAlwaysOn() {
        view.removeOnAttachStateChangeListener(this)
        if (!value) {
            value = true
            listeners.invokeAll(value)
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