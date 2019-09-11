@file:JvmName("LkAndroidDesignExtensions")
@file:JvmMultifileClass

package lk.android.design.extensions



import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import androidx.core.widget.NestedScrollView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

/**
 * Various extensions for working with snackbars.
 * Created by josep on 3/3/2016.
 */

private fun View.setSubviewsTextColor(color: Int) {
    if (this is ViewGroup) {
        for (subviewIndex in 0..childCount) {
            val subview = getChildAt(subviewIndex) ?: continue
            subview.setSubviewsTextColor(color)
        }
    }
    if (this is TextView) {
        this.setTextColor(color)
    }
}

private fun obtainSnackbar(group: ViewGroup): Snackbar {
//    val constructor = Snackbar::class.java.getDeclaredConstructor(ViewGroup::class.java)
//    constructor.isAccessible = true
//    return constructor.newInstance(group)
    return Snackbar.make(group, "", Toast.LENGTH_LONG)
}

/**
 * Launches a snackbar.
 */
fun View.snackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) {
    try {
        val snack = obtainSnackbar(findSuitableParent(this))
        snack.setText(text)
        snack.duration = duration
        snack.view.setSubviewsTextColor(Color.WHITE)
        snack.init()
        snack.show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * Launches a snackbar.
 */
fun View.snackbar(text: Int, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) = snackbar(resources.getString(text), duration, init)

/**
 * Launches a snackbar.
 */
fun Context.snackbar(text: CharSequence, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) {
    var currentContext = this
    loop@ while (currentContext !is Activity) {
        currentContext = when {
            this is Activity -> this
            this is ContextWrapper -> baseContext
            else -> break@loop
        }
    }
    val activity = currentContext as? Activity ?: return
    var view = activity.findViewById<View>(android.R.id.content)
    view = when (view) {
        is ScrollView -> view.getChildAt(0)
        is NestedScrollView -> view.getChildAt(0)
        else -> view
    }
    try {
        view?.snackbar(text, duration, init)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * Launches a snackbar.
 */
fun Context.snackbar(text: Int, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) {
    var currentContext = this
    loop@ while (currentContext !is Activity) {
        currentContext = when {
            this is Activity -> this
            this is ContextWrapper -> baseContext
            else -> break@loop
        }
    }
    val activity = currentContext as? Activity ?: return
    var view = activity.findViewById<View>(android.R.id.content)
    view = when (view) {
        is ScrollView -> view.getChildAt(0)
        is NestedScrollView -> view.getChildAt(0)
        else -> view
    }
    try {
        view?.snackbar(text, duration, init)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * DSL for adding listeners to a snackbar.
 */
fun Snackbar.callback(init: _Snackbar_Callback.() -> Unit) {
    val callback = _Snackbar_Callback()
    callback.init()
    setCallback(callback)
}

class _Snackbar_Callback : Snackbar.Callback() {

    private var _onShown: ((Snackbar?) -> Unit)? = null
    private var _onDismissed: ((Snackbar?, Int) -> Unit)? = null

    override fun onShown(snackbar: Snackbar?) {
        _onShown?.invoke(snackbar)
    }

    fun onSnackbarShown(listener: (Snackbar?) -> Unit) {
        _onShown = listener
    }

    override fun onDismissed(snackbar: Snackbar?, event: Int) {
        _onDismissed?.invoke(snackbar, event)
    }

    fun onSnackbarDismissed(listener: (Snackbar?, Int) -> Unit) {
        _onDismissed = listener
    }
}

private fun findSuitableParent(startView: View?): ViewGroup {
    var view = startView
    var fallback: ViewGroup? = null
    do {
        if (view is CoordinatorLayout) {
            // We've found a CoordinatorLayout, use it
            return view
        } else if (view is FrameLayout && view !is NestedScrollView) {
            if (view.id == android.R.id.content) {
                // If we've hit the decor content view, then we didn't find a CoL in the
                // hierarchy, so use it.
                return view
            } else {
                // It's not the content view but we'll use it as our fallback
                fallback = view as ViewGroup?
            }
        }

        if (view != null) {
            // Else, we will loop and crawl up the view hierarchy and try to find a parent
            val parent = view.parent
            view = if (parent is View) parent else null
        }
    } while (view != null)

    // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
    return fallback ?: throw IllegalStateException()
}