package lk.kotlin.android.example.observable

import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.android.observable.bindString
import lk.anko.extensions.anko
import lk.kotlin.android.example.styleDefault
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

/**
 * A [ViewGenerator] for demonstrating observable binding.
 * Created by josep on 11/10/2016.
 */
class MultipleBindingsVG() : ViewGenerator {

    val textObs = StandardObservableProperty("Start Text")
    var text by textObs

    override fun invoke(access: ActivityAccess): View = access.context.anko().run {
        verticalLayout {

            textView("This is an editable binding to an observable.")
                    .lparams(matchParent, wrapContent) { margin = dip(8) }

            editText {
                styleDefault()

                //This ensures that the value of the observable and the text inside this EditText are always the same
                bindString(textObs)
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            textView("This text field is bound to the observable.")
                    .lparams(matchParent, wrapContent) { margin = dip(8) }

            textView {
                styleDefault()

                //This bind is read only.
                bindString(textObs)
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            textView("This text field is bound to the observable in a more manual way.")
                    .lparams(matchParent, wrapContent) { margin = dip(8) }

            textView {
                styleDefault()

                //This is the more manual way of binding things to a view.  We bind an observable to a callback for the duration of the view's lifecycle.
                this.lifecycle.bind(textObs) {
                    text = it
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }
        }
    }
}