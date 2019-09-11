@file:JvmName("LkAndroidLifecycle")
@file:JvmMultifileClass

package lk.android.lifecycle




import androidx.core.view.ViewCompat
import android.view.View
import android.view.ViewGroup
import com.lightningkite.lk_android_lifecycle.R
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.addAndInvoke
import lk.kotlin.observable.property.lifecycle.Lifecycle
import lk.kotlin.utils.lambda.invokeAll
import java.util.*

//View lifecycle stuff

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

    var matchingRoot: Lifecycle? = null
    val matchListener = { it:Boolean ->
        value = it
        listeners.invokeAll(value)
    }

    fun setAlwaysMatch(root: Lifecycle) {
        if(matchingRoot != root) {
            matchingRoot?.remove(matchListener)
            matchingRoot = root
            view.removeOnAttachStateChangeListener(this)
            root.addAndInvoke(matchListener)
        }
    }

    fun setAlwaysMatchRecursive(root: Lifecycle) {
        setAlwaysMatch(root)
        view.forThisAndAllChildrenRecursive {
            it.lifecycleOrNull?.setAlwaysMatch(root)
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
    get() {
        val old = getTag(R.id.lk_lifecycle)
        if(old is ViewLifecycleListener) return old
        val listener = ViewLifecycleListener(this)
        addOnAttachStateChangeListener(listener)
        setTag(R.id.lk_lifecycle, listener)
        return listener
    }

/**
 * Gets this view's lifecycle object for events to connect with.
 */
val View.lifecycleOrNull: ViewLifecycleListener?
    get() = getTag(R.id.lk_lifecycle) as? ViewLifecycleListener