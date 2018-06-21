package lk.kotlin.android.example

import android.content.res.Resources
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.extensions.selectableItemBackgroundResource
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.adapters.observable.listAdapter
import lk.anko.extensions.anko
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.observable.property.StackObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

class DemoSelectorVG(val stack: StackObservableProperty<ViewGenerator>, val value: DemoGroupSelectorVG.DemoGroup) : ViewGenerator, HasTitle {

    override fun getTitle(resources: Resources): String = value.name

    override fun invoke(access: ActivityAccess): View = access.context.anko().run {
        verticalLayout {

            textView(R.string.welcome_message) {
                minimumHeight = dip(48)
                padding = dip(16)
                gravity = Gravity.CENTER
            }

            verticalRecyclerView {
                adapter = listAdapter(value.demos) { itemObs ->
                    textView {
                        minimumHeight = dip(48)
                        padding = dip(16)
                        backgroundResource = selectableItemBackgroundResource
                        lifecycle.bind(itemObs) {
                            text = it.name
                        }
                        setOnClickListener { it: View? ->
                            stack.push(itemObs.value.maker.invoke(stack))
                        }
                    }.lparams(matchParent, wrapContent)
                }
            }
        }
    }
}