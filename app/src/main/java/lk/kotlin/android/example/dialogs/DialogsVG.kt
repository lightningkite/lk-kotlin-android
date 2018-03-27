package lk.kotlin.android.example.dialogs

import android.graphics.Color
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.dialogs.confirmationDialog
import lk.android.dialogs.dialog
import lk.android.dialogs.infoDialog
import lk.android.dialogs.inputDialog
import lk.android.extensions.FullInputType
import lk.android.mighty.view.ViewGenerator
import lk.anko.extensions.anko
import lk.kotlin.android.example.R
import org.jetbrains.anko.*

/**
 * This example is meant to demonstrate how to use the animation package's features.
 */
class DialogsVG() : ViewGenerator {

    override fun invoke(access: ActivityAccess): View = access.context.anko().scrollView {
        verticalLayout {
            gravity = Gravity.CENTER
            padding = dip(8)

            button {
                text = "Open Info Dialog"
                setOnClickListener {
                    context.infoDialog(
                            title = "Hello!",
                            message = "This is some information you should know."
                    )
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            button {
                text = "Open Confirmation Dialog"
                setOnClickListener {
                    context.confirmationDialog(
                            title = "Confirm",
                            message = "Are you sure you want to trigger another dialog?"
                    ) {
                        context.infoDialog(message = "OK, done.")
                    }
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            button {
                text = "Open Input Dialog"
                setOnClickListener {
                    context.inputDialog(
                            title = "Enter Info",
                            message = "What's your name?",
                            inputType = FullInputType.NAME,
                            validation = { if (it.isBlank()) R.string.validation_name_blank else null }
                    ) {
                        context.infoDialog(message = "Hello, $it!")
                    }
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            button {
                text = "Open Crazy Dialog"
                setOnClickListener {
                    context.dialog { access ->
                        access.context.anko().frameLayout {
                            backgroundColor = Color.WHITE
                            padding = dip(8)

                            verticalLayout {
                                backgroundColor = 0xFF8080FF.toInt()
                                padding = dip(8)

                                textView {
                                    text = "Hi!  This is UGLY and custom."
                                }.lparams(matchParent, wrapContent) { margin = dip(8) }

                                button {
                                    text = "Close Me"
                                    setOnClickListener {
                                        access.activity?.finish()
                                    }
                                }.lparams(matchParent, wrapContent) { margin = dip(8) }

                            }.lparams(matchParent, wrapContent)
                        }
                    }
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }
        }
    }
}