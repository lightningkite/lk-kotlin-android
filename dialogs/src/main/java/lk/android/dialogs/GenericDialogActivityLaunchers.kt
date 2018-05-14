@file:JvmName("LkAndroidDialogs")
@file:JvmMultifileClass

package lk.android.dialogs



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
import lk.android.activity.access.ActivityAccess
import lk.android.dialogs.StandardDialog.standardMargins
import lk.android.dialogs.StandardDialog.styleMessage
import lk.android.dialogs.StandardDialog.styleNormal
import lk.android.dialogs.StandardDialog.styleTitle

/**
 * Opens an instance of [GenericDialogActivity].
 * @param dismissible Whether or not the dialog can be dismissed without selecting an option.
 * @param windowModifier An optional lambda for modifying the window.
 * @param layoutParamModifier An optional lambda for modifying the layout parameters of the activity.
 * @param viewGenerator A lambda that generates a view for the dialog.
 */
fun Context.dialog(
        dismissible: Boolean = true,
        windowModifier: Window.() -> Unit = {},
        layoutParamModifier: WindowManager.LayoutParams.() -> Unit = {},
        theme: Int? = null,
        viewGenerator: (ActivityAccess) -> View
) {
    val id: Int = viewGenerator.hashCode()
    GenericDialogActivity.containers[id] = GenericDialogActivity.ContainerData(viewGenerator, layoutParamModifier, windowModifier, theme)
    startActivity(Intent(this, GenericDialogActivity::class.java).apply {
        putExtra(GenericDialogActivity.EXTRA_CONTAINER, id)
        putExtra(GenericDialogActivity.EXTRA_DISMISS_ON_TOUCH_OUTSIDE, dismissible)
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

/**
 * Various stylings for default dialogs.
 */
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

/**
 * Creates a psuedo-dialog that is actually an activity.  Significantly more stable and safe.
 * @param title Optional; a text resource for the title.
 * @param message Optional; a text resource for the message.
 * @param buttons The buttons the dialog should have, defined as a list of pairs between the button label and what it should do.
 * @param dismissible Whether or not the dialog can be dismissed without selecting an option.
 * @param content Optional; additional content the dialog should have.
 */
fun Context.standardDialog(
        title: Int?,
        message: Int?,
        buttons: List<Pair<String, (ActivityAccess) -> Unit>>,
        dismissible: Boolean = true,
        content: ((ActivityAccess) -> View)? = null
) = standardDialog(
        if (title != null) resources.getString(title) else null,
        if (message != null) resources.getString(message) else null,
        buttons,
        dismissible,
        content
)

/**
 * Creates a psuedo-dialog that is actually an activity.  Significantly more stable and safe.
 * @param title Optional; text for the title.
 * @param message Optional; text for the message.
 * @param buttons The buttons the dialog should have, defined as a list of pairs between the button label and what it should do.
 * @param dismissible Whether or not the dialog can be dismissed without selecting an option.
 * @param content Optional; additional content the dialog should have.
 */
fun Context.standardDialog(
        title: String?,
        message: String?,
        buttons: List<Pair<String, (ActivityAccess) -> Unit>>,
        dismissible: Boolean = true,
        content: ((ActivityAccess) -> View)? = null
) {
    dialog(dismissible, layoutParamModifier = { width = MATCH_PARENT }, viewGenerator = { access ->
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

/**
 * Creates a confirmation dialog that is actually an activity.  Significantly more stable and safe.
 * @param title Optional; a text resource for the title.
 * @param message A text resource for the message.
 * @param okResource A text resource for the OK button.
 * @param cancelResource A text resource for the cancel button.
 * @param onCancel A lambda called when 'Cancel' is clicked.
 * @param onConfirm A lambda called when 'OK' is clicked.
 */
fun Context.confirmationDialog(title: String? = null, message: String, okResource: Int = android.R.string.ok, cancelResource: Int = android.R.string.cancel, dismissible: Boolean = true, onCancel: () -> Unit = {}, onConfirm: () -> Unit) {
    return standardDialog(
            title,
            message,
            listOf(StandardDialog.cancelButton(resources, cancelResource, onCancel), StandardDialog.okButton(resources, okResource, onConfirm)),
            dismissible = dismissible)
}

/**
 * Creates a confirmation dialog that is actually an activity.  Significantly more stable and safe.
 * @param title Optional; text for the title.
 * @param message Text for the message.
 * @param okResource A text resource for the OK button.
 * @param cancelResource A text resource for the cancel button.
 * @param onCancel A lambda called when 'Cancel' is clicked.
 * @param onConfirm A lambda called when 'OK' is clicked.
 */
fun Context.confirmationDialog(title: Int? = null, message: Int, okResource: Int = android.R.string.ok, cancelResource: Int = android.R.string.cancel, dismissible: Boolean = true, onCancel: () -> Unit = {}, onConfirm: () -> Unit) {
    return standardDialog(
            title,
            message,
            listOf(StandardDialog.cancelButton(resources, cancelResource, onCancel), StandardDialog.okButton(resources, okResource, onConfirm)),
            dismissible = dismissible)
}

/**
 * Creates a information dialog that is actually an activity.  Significantly more stable and safe.
 * @param title Optional; text for the title.
 * @param message Text for the message.
 * @param content Additional content views for the message.
 * @param onConfirm A lambda called when 'OK' is clicked.
 */
fun Context.infoDialog(title: Int? = null, message: Int?, content: ((ActivityAccess) -> View)? = null, onConfirm: () -> Unit = {}) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm)), content = content)
}

/**
 * Creates a information dialog that is actually an activity.  Significantly more stable and safe.
 * @param title Optional; text for the title.
 * @param message Text for the message.
 * @param content Additional content views for the message.
 * @param onConfirm A lambda called when 'OK' is clicked.
 */
fun Context.infoDialog(title: String? = null, message: String?, content: ((ActivityAccess) -> View)? = null, onConfirm: () -> Unit = {}) {
    return standardDialog(title, message, listOf(StandardDialog.okButton(resources, action = onConfirm)), content = content)
}


/**
 * Creates an input dialog that is actually an activity.  Significantly more stable and safe.
 * @param title Optional; text for the title.
 * @param message Text for the message.
 * @param hint Text for the hint on the input.
 * @param defaultValue The default value in the text box.
 * @param inputType The input type for the stuff.  See [InputType].
 * @param dismissible Whether the dialog can be cancelled.
 * @param validation A lambda that checks the input.  If the string is OK, it returns null.  Otherwise, it returns an error text resource.
 * @param onResult A lambda called when 'OK' is clicked, holding the input of the result.
 */
fun Context.inputDialog(
        title: Int,
        message: Int,
        hint: Int = 0,
        defaultValue: String = "",
        inputType: Int = InputType.TYPE_CLASS_TEXT,
        dismissible: Boolean = true,
        validation: (String) -> Int? = { null },
        onResult: (String?) -> Unit
) {
    return inputDialog(
            title = resources.getString(title),
            message = resources.getString(message),
            hint = if (hint == 0) "" else resources.getString(hint),
            defaultValue = defaultValue,
            inputType = inputType,
            dismissible = dismissible,
            validation = validation,
            onResult = onResult
    )
}


/**
 * Creates an input dialog that is actually an activity.  Significantly more stable and safe.
 * @param title Optional; text for the title.
 * @param message Text for the message.
 * @param hint Text for the hint on the input.
 * @param defaultValue The default value in the text box.
 * @param inputType The input type for the stuff.  See [InputType].
 * @param dismissible Whether the dialog can be cancelled.
 * @param validation A lambda that checks the input.  If the string is OK, it returns null.  Otherwise, it returns an error text resource.
 * @param onResult A lambda called when 'OK' is clicked, holding the input of the result.
 */
fun Context.inputDialog(
        title: String,
        message: String,
        hint: String = "",
        defaultValue: String = "",
        inputType: Int = InputType.TYPE_CLASS_TEXT,
        dismissible: Boolean = true,
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
            dismissible,
            {
                EditText(this).apply {
                    this.setTextColor(Color.BLACK)
                    this.setHintTextColor(Color.GRAY)
                    this.setText(defaultValue)
                    this.inputType = inputType
                    this.hint = hint
                }.also { et = it }
            }
    )
}