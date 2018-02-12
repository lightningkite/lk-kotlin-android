package com.lightningkite.kotlin.anko.viewcontrollers.dialogs

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.lightningkite.kotlin.anko.hideSoftInput
import com.lightningkite.kotlin.anko.selectableItemBackgroundBorderlessResource
import com.lightningkite.kotlin.anko.snackbar
import com.lightningkite.kotlin.anko.textColorResource
import com.lightningkite.kotlin.anko.viewcontrollers.containers.VCStack
import com.lightningkite.kotlin.anko.viewcontrollers.implementations.dialog
import org.jetbrains.anko.*

private inline fun ViewGroup.MarginLayoutParams.standardMargins(ctx: Context) {
    leftMargin = ctx.dip(16)
    rightMargin = ctx.dip(16)
    topMargin = ctx.dip(8)
    bottomMargin = ctx.dip(8)
}

private inline fun TextView.styleTitle() {
    textSize = 18f
    setTypeface(null, Typeface.BOLD)
    textColorResource = android.R.color.primary_text_light
}

private inline fun TextView.styleMessage() {
    textSize = 16f
    textColorResource = android.R.color.secondary_text_light
}

private inline fun Button.styleNormal() {
    textSize = 16f
    textColorResource = android.R.color.secondary_text_light
    setAllCaps(true)
    backgroundResource = selectableItemBackgroundBorderlessResource
}

private inline fun Button.styleDestructive() {
    textSize = 16f
    textColor = Color.RED
    setAllCaps(true)
}

object StandardDialog {
    fun okButton(resources: Resources, okResource: Int = android.R.string.ok, action: () -> Unit = {}): Pair<String, (VCStack) -> Unit> =
            resources.getString(okResource) to { it: VCStack -> action(); it.pop() }

    fun cancelButton(resources: Resources, cancelResource: Int = android.R.string.cancel, action: () -> Unit = {}): Pair<String, (VCStack) -> Unit> =
            resources.getString(cancelResource) to { it: VCStack -> action(); it.pop() }

    fun cancelButton(resources: Resources): Pair<String, (VCStack) -> Unit> = resources.getString(android.R.string.cancel) to { it: VCStack -> it.pop() }


    inline fun ViewGroup.MarginLayoutParams.standardMargins(ctx: Context) {
        leftMargin = ctx.dip(16)
        rightMargin = ctx.dip(16)
        topMargin = ctx.dip(8)
        bottomMargin = ctx.dip(8)
    }

    inline fun TextView.styleTitle() {
        textSize = 18f
        setTypeface(null, Typeface.BOLD)
        textColorResource = android.R.color.primary_text_light
    }

    inline fun TextView.styleMessage() {
        textSize = 16f
        textColorResource = android.R.color.secondary_text_light
    }

    inline fun Button.styleNormal() {
        textSize = 16f
        textColorResource = android.R.color.secondary_text_light
        setAllCaps(true)
        backgroundResource = selectableItemBackgroundBorderlessResource
    }

    private inline fun Button.styleDestructive() {
        textSize = 16f
        textColor = Color.RED
        setAllCaps(true)
    }
}

@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.alertDialog(message)",
                "com.lightningkite.kotlin.anko.activity.alertDialog"
        )
)
fun Context.alertDialog(message: Int) = standardDialog(
        null,
        resources.getString(message),
        listOf(StandardDialog.okButton(resources) {}),
        true,
        null
)

@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.standardDialog(title = title, message = message, buttons = buttons, dismissOnClickOutside = dismissOnClickOutside, content = content)",
                "com.lightningkite.kotlin.anko.activity.standardDialog"
        )
)
fun Context.standardDialog(
        title: Int?,
        message: Int?,
        buttons: List<Pair<String, (VCStack) -> Unit>>,
        dismissOnClickOutside: Boolean = true,
        content: (ViewGroup.(VCStack) -> View)? = null
) = standardDialog(
        if (title != null) resources.getString(title) else null,
        if (message != null) resources.getString(message) else null,
        buttons,
        dismissOnClickOutside,
        content
)

object CustomDialog {
    fun okButton(resources: Resources, okResource: Int = android.R.string.ok, action: () -> Unit = {}, okStyle: (Button) -> Unit): Triple<String, (VCStack) -> Unit, (Button) -> Unit> =
            Triple(resources.getString(okResource), { it: VCStack -> action(); it.pop() }, okStyle)

    fun cancelButton(resources: Resources, cancelResource: Int = android.R.string.cancel, action: () -> Unit = {}, cancelStyle: (Button) -> Unit): Triple<String, (VCStack) -> Unit, (Button) -> Unit> =
            Triple(resources.getString(cancelResource), { it: VCStack -> action(); it.pop() }, cancelStyle)
}

/**
 * Creates a psuedo-dialog that is actually an activity.  Significantly more stable and safe.
 */
@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.standardDialog(title = title, message = message, buttons = buttons, dismissOnClickOutside = dismissOnClickOutside, content = content)",
                "com.lightningkite.kotlin.anko.activity.standardDialog"
        )
)
fun Context.standardDialog(
        title: String?,
        message: String?,
        buttons: List<Pair<String, (VCStack) -> Unit>>,
        dismissOnClickOutside: Boolean = true,
        content: (ViewGroup.(VCStack) -> View)? = null
) {
    return dialog(dismissOnClickOutside, layoutParamModifier = { width = matchParent }) { ui, vcStack ->
        ui.scrollView {
            verticalLayout {
                //title
                textView(text = title) {
                    styleTitle()
                    if (title.isNullOrEmpty()) {
                        visibility = View.GONE
                    }
                }.lparams(matchParent, wrapContent) {
                    standardMargins(context)
                    topMargin = dip(16)
                }

                //message
                textView(text = message) {
                    styleMessage()
                    if (message.isNullOrEmpty()) {
                        visibility = View.GONE
                    }
                }.lparams(matchParent, wrapContent) {
                    standardMargins(context)
                }

                //custom content
                content?.invoke(this, vcStack)?.lparams(matchParent, wrapContent) {
                    standardMargins(context)
                }

                //buttons
                linearLayout {
                    gravity = Gravity.END
                    for ((buttonName, action) in buttons) {
                        button(buttonName) {
                            styleNormal()
                            setOnClickListener {
                                action(vcStack)
                            }
                        }.lparams(wrapContent, wrapContent) {
                            standardMargins(context)
                        }
                    }
                }.lparams(matchParent, wrapContent)
            }
        }
    }
}

@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.standardDialog(title = title, message = message, buttons = buttons, dismissOnClickOutside = dismissOnClickOutside, content = content)",
                "com.lightningkite.kotlin.anko.activity.standardDialog"
        )
)
fun Context.customDialog(
        title: Int?,
        message: Int,
        buttons: List<Triple<String, (VCStack) -> Unit, (Button) -> Unit>>,
        dismissOnClickOutside: Boolean = true,
        content: (ViewGroup.(VCStack) -> View)? = null
) = customDialog(
        if (title != null) resources.getString(title) else null,
        resources.getString(message),
        buttons,
        dismissOnClickOutside,
        content
)

/**
 * Creates a psuedo-dialog that is actually an activity.  Significantly more stable and safe.
 */
@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.standardDialog(title = title, message = message, buttons = buttons, dismissOnClickOutside = dismissOnClickOutside, content = content)",
                "com.lightningkite.kotlin.anko.activity.standardDialog"
        )
)
fun Context.customDialog(
        title: String?,
        message: String,
        buttons: List<Triple<String, (VCStack) -> Unit, (Button) -> Unit>>,
        dismissOnClickOutside: Boolean = true,
        content: (ViewGroup.(VCStack) -> View)? = null
) {
    return dialog(dismissOnClickOutside, layoutParamModifier = { width = matchParent }) { ui, vcStack ->
        ui.scrollView {
            verticalLayout {
                //title
                textView(text = title) {
                    styleTitle()
                    if (title.isNullOrEmpty()) {
                        visibility = View.GONE
                    }
                }.lparams(matchParent, wrapContent) {
                    standardMargins(context)
                    topMargin = dip(16)
                }

                //message
                textView(text = message) {
                    styleMessage()
                }.lparams(matchParent, wrapContent) {
                    standardMargins(context)
                }

                //custom content
                content?.invoke(this, vcStack)?.lparams(matchParent, wrapContent) {
                    standardMargins(context)
                }

                //buttons
                linearLayout {
                    gravity = Gravity.END
                    buttons.forEach { triple ->
                        button(triple.first) {
                            setOnClickListener { triple.second(vcStack) }
                            triple.third.invoke(this)
                        }.lparams {
                            standardMargins(context)
                        }
                    }
                }.lparams(matchParent, wrapContent)
            }
        }
    }
}

@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.confirmationDialog(title = title, message = message, onCancel = onCancel, onConfirm = onConfirm)",
                "com.lightningkite.kotlin.anko.activity.confirmationDialog"
        )
)
fun Context.confirmationDialog(title: Int? = null, message: Int, onCancel: () -> Unit = {}, onConfirm: () -> Unit) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm), StandardDialog.cancelButton(resources, action = onCancel)))
}

@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.confirmationDialog(title = title, message = message, onCancel = onCancel, onConfirm = onConfirm)",
                "com.lightningkite.kotlin.anko.activity.confirmationDialog"
        )
)
fun Context.confirmationDialog(title: String? = null, message: String, onCancel: () -> Unit = {}, onConfirm: () -> Unit) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm), StandardDialog.cancelButton(resources, action = onCancel)))
}

@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                """this.confirmationDialog(title = title, message = message, okResource = okResource, cancelResource = cancelResource, dismissOnClickOutside = dismissOnClickOutside, onPositiveAction = onPositiveAction, onNegativeAction = onNegativeAction )""",
                "com.lightningkite.kotlin.anko.activity.confirmationDialog"
        )
)
fun Context.confirmationDialog(title: String? = null, message: String, okResource: Int = android.R.string.ok, cancelResource: Int = android.R.string.cancel, dismissOnClickOutside: Boolean = true, onPositiveAction: () -> Unit, onNegativeAction: () -> Unit) {
    return standardDialog(
            title,
            message,
            listOf(StandardDialog.okButton(resources, okResource, onPositiveAction), StandardDialog.cancelButton(resources, cancelResource, onNegativeAction)),
            dismissOnClickOutside = dismissOnClickOutside)
}

@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.confirmationDialog(title = title, message = message, okResource = okResource, cancelResource = cancelResource, dismissOnClickOutside = dismissOnClickOutside, onPositiveAction = onPositiveAction, onNegativeAction = onNegativeAction )",
                "com.lightningkite.kotlin.anko.activity.confirmationDialog"
        )
)
fun Context.confirmationDialog(title: Int? = null, message: Int, okResource: Int = android.R.string.ok, cancelResource: Int = android.R.string.cancel, dismissOnClickOutside: Boolean = true, onPositiveAction: () -> Unit, onNegativeAction: () -> Unit) {
    return standardDialog(
            title,
            message,
            listOf(StandardDialog.okButton(resources, okResource, onPositiveAction), StandardDialog.cancelButton(resources, cancelResource, onNegativeAction)),
            dismissOnClickOutside = dismissOnClickOutside)
}

@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.infoDialog(title = title, messsage = message, content = content, onConfirm = onConfirm)",
                "com.lightningkite.kotlin.anko.activity.infoDialog"
        )
)
fun Context.infoDialog(title: Int? = null, message: Int?, content: (ViewGroup.(VCStack) -> View)? = null, onConfirm: () -> Unit = {}) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm)), content = content)
}

@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                "this.infoDialog(title = title, messsage = message, content = content, onConfirm = onConfirm)",
                "com.lightningkite.kotlin.anko.activity.infoDialog"
        )
)
fun Context.infoDialog(title: String? = null, message: String?, content: (ViewGroup.(VCStack) -> View)? = null, onConfirm: () -> Unit = {}) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm)), content = content)
}


/**
 * Creates a dialog with an input text field on it.
 */
@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                """this.inputDialog(title = title, message = message, hint = hint, defaultValue = defaultValue, inputType = inputType, canCancel = canCancel, validation = validation, onResult = onResult)""",
                "com.lightningkite.kotlin.anko.activity.inputDialog"
        )
)
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
@Deprecated(
        "Use the other dialog function that is not dependent on VCs.",
        ReplaceWith(
                """this.inputDialog(title = title, message = message, hint = hint, defaultValue = defaultValue, inputType = inputType, canCancel = canCancel, validation = validation, onResult = onResult)""",
                "com.lightningkite.kotlin.anko.activity.inputDialog"
        )
)
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
                    resources.getString(android.R.string.cancel)!! to { it: VCStack ->
                        et?.hideSoftInput()
                        onResult(null)
                        it.pop()
                    },
                    resources.getString(android.R.string.ok)!! to { it: VCStack ->
                        if (et != null) {
                            et?.hideSoftInput()
                            val result = et!!.text.toString()
                            val error = validation(result)
                            if (error == null) {
                                onResult(result)
                                it.pop()
                            } else {
                                snackbar(error)
                            }
                        }
                    }
            ),
            canCancel,
            {
                et = editText() {
                    this.setText(defaultValue)
                    this.inputType = inputType
                    this.hint = hint
                }
                et!!
            }
    )
}