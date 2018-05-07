package lk.kotlin.android.example.animations.observable

import android.graphics.Color
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.animations.observable.expanding
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.extensions.anko
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

/**
 * Demonstrates the use of .expanding()
 * Created by joseph on 3/27/18.
 */
class ExpandingExampleVG : ViewGenerator {

    val itemExpanded = StandardObservableProperty(false)

    override fun invoke(access: ActivityAccess): View = access.context.anko().verticalLayout {
        gravity = Gravity.CENTER

        button() {
            lifecycle.bind(itemExpanded) {
                text = if (it)
                    "Tap me to collapse"
                else
                    "Tap me to expand"
            }
            gravity = Gravity.CENTER
            padding = dip(8)

            setOnClickListener {
                itemExpanded.value = !itemExpanded.value
            }
        }.lparams(matchParent, wrapContent)

        textView {
            backgroundColor = Color.RED
            text = "I'm the collapsing view.\nFrequently it's a good idea to keep information that the user doesn't need to see all the time under something that collapses."
            gravity = Gravity.CENTER_HORIZONTAL
            padding = dip(8)
        }.lparams(matchParent, wrapContent).expanding(itemExpanded)
    }
}