package lk.kotlin.android.example.design.extensions

import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.design.extensions.*
import lk.android.extensions.selectableItemBackgroundResource
import lk.android.mighty.view.ViewGenerator
import lk.anko.extensions.anko
import lk.anko.extensions.textInputEditText
import lk.kotlin.android.example.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.textInputLayout

/**
 * This example is meant to demonstrate how to use the animation package's features.
 */
class DesignExtensionsVG() : ViewGenerator {

    override fun invoke(access: ActivityAccess): View = access.context.anko().scrollView {
        verticalLayout {
            gravity = Gravity.CENTER
            padding = dip(8)

            button {
                text = "Open Snackbar"
                setOnClickListener {
                    snackbar("Hello!")
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            tabLayout {
                setTabTextSize(12f)
                setTabBackgroundResource(selectableItemBackgroundResource)
                addTab(newTab().setText("First"))
                addTab(newTab().setText("Second"))
                addTab(newTab().setText("Third"))
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            textInputLayout {
                errorResource = R.string.error
                hintResource = R.string.hint
                textInputEditText {
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }
        }
    }
}