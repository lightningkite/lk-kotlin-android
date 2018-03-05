package lk.android.mighty.view

import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import lk.android.dialogs.dialog
import lk.kotlin.observable.property.StackObservableProperty
import lk.android.animations.SwapView


fun Context.stackDialog(
        dismissOnTouchOutside: Boolean = true,
        windowModifier: Window.() -> Unit = {},
        layoutParamModifier: WindowManager.LayoutParams.() -> Unit = {},
        stack:StackObservableProperty<ViewGenerator>
) = this.dialog(
        dismissOnTouchOutside = dismissOnTouchOutside,
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

fun Context.stackDialog(
        dismissOnTouchOutside: Boolean = true,
        windowModifier: Window.() -> Unit = {},
        layoutParamModifier: WindowManager.LayoutParams.() -> Unit = {},
        buildStack:(StackObservableProperty<ViewGenerator>)->ViewGenerator
) = this.stackDialog(
        dismissOnTouchOutside = dismissOnTouchOutside,
        windowModifier = windowModifier,
        layoutParamModifier = layoutParamModifier,
        stack = StackObservableProperty(buildStack)
)