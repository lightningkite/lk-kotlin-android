package lk.android.mighty.view

import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import lk.android.animations.SwapView
import lk.android.dialogs.dialog
import lk.kotlin.observable.property.StackObservableProperty

/**
 * Opens a dialog holding a stack of [ViewGenerator]s.
 * When the final one is popped, then the dialog will dismiss itself.
 */
fun Context.stackDialog(
        stack: StackObservableProperty<ViewGenerator>,
        dismissible: Boolean = true,
        windowModifier: Window.() -> Unit = {},
        layoutParamModifier: WindowManager.LayoutParams.() -> Unit = {}
) = this.dialog(
        dismissible = dismissible,
        windowModifier = windowModifier,
        layoutParamModifier = layoutParamModifier,
        viewGenerator = { access ->
            stack.prepend {
                it.activity?.finish()
                View(it.context)
            }
            SwapView(access.context).apply {
                bind(access, stack)
            }
        }
)