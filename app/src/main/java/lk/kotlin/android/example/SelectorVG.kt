package lk.kotlin.android.example

import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.extensions.selectableItemBackgroundResource
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.adapters.observable.listAdapter
import lk.anko.extensions.anko
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*
import java.util.*

/**
 * A [ViewGenerator] for selecting which demo you want to view.
 * Created by jivie on 5/5/16.
 */
class SelectorVG(val main: MainVG) : ViewGenerator {

    override fun toString(): String = "Demo Selector"

    val demos: ArrayList<Pair<String, () -> ViewGenerator>> = arrayListOf(
            "Activity Access Example" to { lk.kotlin.android.example.activity.access.ExampleVG() },
            "Height Animator Example" to { lk.kotlin.android.example.animations.HeightAnimatorVG() },
            "Transition View Example" to { lk.kotlin.android.example.animations.TransitionViewVG() },
            "Swap View Example" to { lk.kotlin.android.example.animations.SwapViewVG() },

            "Example Login" to {
                ExampleLoginVG({
                    main.stack.pop()
                })
            },
            "Observable List" to { ObservableListVG() },
            "Observable List 2" to { ObservableList2VG() },
            "Network Image" to { NetImageTestVG() },
            "Observable Property" to { ObservablePropertyTestVG() },
            "View Controller Stacks" to { StackDemoVG(main.stack) },
            "Coordinator Layout" to { CoordinatorLayoutTestVG() },
            "List from Network" to { NetworkListVG() },
            "Just a Lambda View" to {
                { access: ActivityAccess ->
                    access.context.anko().run {
                        verticalLayout {
                            gravity = Gravity.CENTER
                            textView {
                                text = "I'm just a lambda - not even a class!"
                            }
                        }
                    }
                }
            }
    )

    override fun invoke(access: ActivityAccess): View = access.context.anko().run {
        verticalLayout {

            textView(R.string.welcome_message) {
                minimumHeight = dip(48)
                padding = dip(16)
                gravity = Gravity.CENTER
            }

            verticalRecyclerView {
                adapter = listAdapter(demos) { itemObs ->
                    textView {
                        minimumHeight = dip(48)
                        padding = dip(16)
                        backgroundResource = selectableItemBackgroundResource
                        lifecycle.bind(itemObs) {
                            text = it.first
                        }
                        setOnClickListener { it: View? ->
                            main.stack.push(itemObs.value.second())
                        }
                    }.lparams(matchParent, wrapContent)
                }
            }
        }
    }
}