package lk.kotlin.android.example.extensions

import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.extensions.selector
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.extensions.anko
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

/**
 * This example is meant to demonstrate how to use the animation package's features.
 */
class SelectorVG() : ViewGenerator {

    val selectedStage = StandardObservableProperty("None")

    override fun invoke(access: ActivityAccess): View = access.context.anko().scrollView {
        verticalLayout {
            gravity = Gravity.CENTER
            padding = dip(8)

            textView {
                gravity = Gravity.CENTER
                lifecycle.bind(selectedStage) {
                    text = "Currently selected stage: $it"
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            button {
                text = "Open Selector"
                setOnClickListener {
                    context.selector(
                            title = "Pick a stage",
                            pairs = listOf(
                                    "Final Destination" to {
                                        selectedStage.value = "Final Destination"
                                    },
                                    "Hyrule Castle" to {
                                        selectedStage.value = "Hyrule Castle"
                                    },
                                    "Peach's Castle" to {
                                        selectedStage.value = "Peach's Castle"
                                    }
                            )
                    )
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }
        }
    }
}