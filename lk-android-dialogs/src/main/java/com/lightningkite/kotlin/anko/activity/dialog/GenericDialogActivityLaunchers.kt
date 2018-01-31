@file:JvmName("LkAndroidDialogs")
@file:JvmMultifileClass

package com.lightningkite.kotlin.anko.activity.dialog

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.res.ResourcesCompat
import android.text.InputType
import android.util.TypedValue
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.lightningkite.kotlin.anko.activity.ActivityAccess
import com.lightningkite.kotlin.anko.activity.dialog.StandardDialog.standardMargins
import com.lightningkite.kotlin.anko.activity.dialog.StandardDialog.styleMessage
import com.lightningkite.kotlin.anko.activity.dialog.StandardDialog.styleNormal
import com.lightningkite.kotlin.anko.activity.dialog.StandardDialog.styleTitle

/**
 * Opens an instance of [GenericDialogActivity].
 */
fun Context.dialog(
        dismissOnTouchOutside: Boolean = true,
        windowModifier: Window.() -> Unit = {},
        layoutParamModifier: WindowManager.LayoutParams.() -> Unit = {},
        viewGenerator: (ActivityAccess) -> View
) {
    val id: Int = viewGenerator.hashCode()
    GenericDialogActivity.containers[id] = GenericDialogActivity.ContainerData(viewGenerator, layoutParamModifier, windowModifier)
    startActivity(Intent(this, GenericDialogActivity::class.java).apply {
        putExtra(GenericDialogActivity.EXTRA_CONTAINER, id)
        putExtra(GenericDialogActivity.EXTRA_DISMISS_ON_TOUCH_OUTSIDE, dismissOnTouchOutside)
    })
}

private fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()
private fun View.selectableItemBackgroundBorderlessResource(): Int {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        // If we're running on Honeycomb or newer, then we can use the Theme's
        // selectableItemBackground to ensure that the View has a pressed state
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true)
        return outValue.resourceId
    }
    return 0
}

object StandardDialog {
    fun okButton(resources: Resources, okResource: Int = android.R.string.ok, action: () -> Unit = {}): Pair<String, (ActivityAccess) -> Unit> =
            resources.getString(okResource) to { it: ActivityAccess -> action(); it.activity?.finish(); Unit }

    fun cancelButton(resources: Resources, cancelResource: Int = android.R.string.cancel, action: () -> Unit = {}): Pair<String, (ActivityAccess) -> Unit> =
            resources.getString(cancelResource) to { it: ActivityAccess -> action(); it.activity?.finish(); Unit }

    fun cancelButton(resources: Resources): Pair<String, (ActivityAccess) -> Unit> = resources.getString(android.R.string.cancel) to { it: ActivityAccess -> it.activity?.finish(); Unit }


    fun ViewGroup.MarginLayoutParams.standardMargins(ctx: Context) {
        leftMargin = ctx.dip(16)
        rightMargin = ctx.dip(16)
        topMargin = ctx.dip(8)
        bottomMargin = ctx.dip(8)
    }

    fun TextView.styleTitle() {
        textSize = 18f
        setTypeface(null, Typeface.BOLD)
        setTextColor(ResourcesCompat.getColor(resources, android.R.color.primary_text_light, null))
    }

    fun TextView.styleMessage() {
        textSize = 16f
        setTextColor(ResourcesCompat.getColor(resources, android.R.color.secondary_text_light, null))
    }

    fun Button.styleNormal() {
        textSize = 16f
        setTextColor(ResourcesCompat.getColor(resources, android.R.color.secondary_text_light, null))
        setAllCaps(true)
        setBackgroundResource(selectableItemBackgroundBorderlessResource())
    }

    fun Button.styleDestructive() {
        textSize = 16f
        setTextColor(Color.RED)
        setAllCaps(true)
        setBackgroundResource(selectableItemBackgroundBorderlessResource())
    }
}

fun Context.alertDialog(message: Int) = standardDialog(
        null,
        resources.getString(message),
        listOf(StandardDialog.okButton(resources) {}),
        true,
        null
)

fun Context.standardDialog(
        title: Int?,
        message: Int?,
        buttons: List<Pair<String, (ActivityAccess) -> Unit>>,
        dismissOnClickOutside: Boolean = true,
        content: ((ActivityAccess) -> View)? = null
) = standardDialog(
        if (title != null) resources.getString(title) else null,
        if (message != null) resources.getString(message) else null,
        buttons,
        dismissOnClickOutside,
        content
)

object CustomDialog {
    fun okButton(resources: Resources, okResource: Int = android.R.string.ok, action: () -> Unit = {}, okStyle: (Button) -> Unit): Triple<String, (ActivityAccess) -> Unit, (Button) -> Unit> =
            Triple(resources.getString(okResource), { it: ActivityAccess -> action(); it.activity?.finish(); Unit }, okStyle)

    fun cancelButton(resources: Resources, cancelResource: Int = android.R.string.cancel, action: () -> Unit = {}, cancelStyle: (Button) -> Unit): Triple<String, (ActivityAccess) -> Unit, (Button) -> Unit> =
            Triple(resources.getString(cancelResource), { it: ActivityAccess -> action(); it.activity?.finish(); Unit }, cancelStyle)
}

/**
 * Creates a psuedo-dialog that is actually an activity.  Significantly more stable and safe.
 */
fun Context.standardDialog(
        title: String?,
        message: String?,
        buttons: List<Pair<String, (ActivityAccess) -> Unit>>,
        dismissOnClickOutside: Boolean = true,
        content: ((ActivityAccess) -> View)? = null
) {
    dialog(dismissOnClickOutside, layoutParamModifier = { width = MATCH_PARENT }, viewGenerator = { access ->
        ScrollView(this).apply {
            addView(LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL

                //title
                addView(TextView(context).apply {
                    styleTitle()
                    text = title
                    if (title.isNullOrEmpty()) {
                        visibility = View.GONE
                    }
                }, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                    standardMargins(context)
                    topMargin = dip(16)
                })

                //message
                addView(TextView(context).apply {
                    styleMessage()
                    text = message
                    if (message.isNullOrEmpty()) {
                        visibility = View.GONE
                    }
                }, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                    standardMargins(context)
                })

                //custom content
                val customContent = content?.invoke(access)
                if (customContent != null) {
                    addView(customContent, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                        standardMargins(context)
                    })
                }

                //buttons
                addView(LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.END
                    for ((buttonName, action) in buttons) {
                        addView(Button(context).apply {
                            text = buttonName
                            styleNormal()
                            setOnClickListener {
                                action(access)
                            }
                        }, LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply { standardMargins(context) })
                    }
                })
            })
        }
    })
}

fun Context.standardDialog(
        title: Int?,
        message: Int,
        buttons: List<Pair<String, (ActivityAccess) -> Unit>>,
        dismissOnClickOutside: Boolean = true,
        content: ((ActivityAccess) -> View)? = null
) {
    standardDialog(
            if (title != null) resources.getString(title) else null,
            resources.getString(message),
            buttons,
            dismissOnClickOutside,
            content
    )
}

fun Context.confirmationDialog(title: Int? = null, message: Int, onCancel: () -> Unit = {}, onConfirm: () -> Unit) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm), StandardDialog.cancelButton(resources, action = onCancel)))
}

fun Context.confirmationDialog(title: String? = null, message: String, onCancel: () -> Unit = {}, onConfirm: () -> Unit) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm), StandardDialog.cancelButton(resources, action = onCancel)))
}

fun Context.confirmationDialog(title: String? = null, message: String, okResource: Int = android.R.string.ok, cancelResource: Int = android.R.string.cancel, dismissOnClickOutside: Boolean = true, onPositiveAction: () -> Unit, onNegativeAction: () -> Unit) {
    return standardDialog(
            title,
            message,
            listOf(StandardDialog.okButton(resources, okResource, onPositiveAction), StandardDialog.cancelButton(resources, cancelResource, onNegativeAction)),
            dismissOnClickOutside = dismissOnClickOutside)
}

fun Context.confirmationDialog(title: Int? = null, message: Int, okResource: Int = android.R.string.ok, cancelResource: Int = android.R.string.cancel, dismissOnClickOutside: Boolean = true, onPositiveAction: () -> Unit, onNegativeAction: () -> Unit) {
    return standardDialog(
            title,
            message,
            listOf(StandardDialog.okButton(resources, okResource, onPositiveAction), StandardDialog.cancelButton(resources, cancelResource, onNegativeAction)),
            dismissOnClickOutside = dismissOnClickOutside)
}

fun Context.infoDialog(title: Int? = null, message: Int?, content: ((ActivityAccess) -> View)? = null, onConfirm: () -> Unit = {}) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm)), content = content)
}

fun Context.infoDialog(title: String? = null, message: String?, content: ((ActivityAccess) -> View)? = null, onConfirm: () -> Unit = {}) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm)), content = content)
}


/**
 * Creates a dialog with an input text field on it.
 */
fun Context.inputDialog(
        title: Int,
        message: Int,
        hint: Int = 0,
        defaultValue: String = "",
        inputType: Int = InputType.TYPE_CLASS_TEXT,
        canCancel: Boolean = true,
        validation: (String) -> Int? = { null },
        onResult: (String?) -> Unit
) {
    return inputDialog(
            title = resources.getString(title),
            message = resources.getString(message),
            hint = if (hint == 0) "" else resources.getString(hint),
            defaultValue = defaultValue,
            inputType = inputType,
            canCancel = canCancel,
            validation = validation,
            onResult = onResult
    )
}


/**
 * Creates a dialog with an input text field on it.
 */
fun Context.inputDialog(
        title: String,
        message: String,
        hint: String = "",
        defaultValue: String = "",
        inputType: Int = InputType.TYPE_CLASS_TEXT,
        canCancel: Boolean = true,
        validation: (String) -> Int? = { null },
        onResult: (String?) -> Unit
) {
    var et: EditText? = null
    standardDialog(
            title,
            message,
            listOf(
                    resources.getString(android.R.string.cancel)!! to { it: ActivityAccess ->
                        if (et != null) {
                            this.getSystemService(Context.INPUT_METHOD_SERVICE).let { it as InputMethodManager }.hideSoftInputFromWindow(et!!.applicationWindowToken, 0)
                        }
                        onResult(null)
                        it.activity?.finish()
                        Unit
                    },
                    resources.getString(android.R.string.ok)!! to { it: ActivityAccess ->
                        if (et != null) {
                            this.getSystemService(Context.INPUT_METHOD_SERVICE).let { it as InputMethodManager }.hideSoftInputFromWindow(et!!.applicationWindowToken, 0)
                            val result = et!!.text.toString()
                            val error = validation(result)
                            if (error == null) {
                                onResult(result)
                                it.activity?.finish()
                                Unit
                            } else {
                                infoDialog(message = error)
                            }
                        }
                    }
            ),
            canCancel,
            {
                EditText(this).apply {
                    this.setText(defaultValue)
                    this.inputType = inputType
                    this.hint = hint
                }.also { et = it }
            }
    )
}