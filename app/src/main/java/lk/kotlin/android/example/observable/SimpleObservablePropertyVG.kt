package lk.kotlin.android.example.observable

import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.extensions.anko
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

/**
 * A [ViewGenerator] for demonstrating observable binding.
 * Created by josep on 11/10/2016.
 */
class SimpleObservablePropertyVG() : ViewGenerator {

    val numberObs = StandardObservableProperty<Int>(0)

    override fun invoke(access: ActivityAccess): View = access.context.anko().scrollView {
        isFillViewport = true

        verticalLayout {

            textView {
                lifecycle.bind(numberObs) {
                    text = "The number currently is $it."
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            button {
                text = "Increment the number"
                setOnClickListener {
                    numberObs.value++
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            button {
                text = "Decrement the number"
                setOnClickListener {
                    numberObs.value--
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

        }.lparams(matchParent, wrapContent)
    }
}