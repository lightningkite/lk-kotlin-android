package com.lightningkite.kotlincomponents

import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.mighty.view.ViewGenerator
import lk.android.extensions.selectableItemBackgroundResource
import lk.android.lifecycle.lifecycle
import lk.anko.activity.access.anko
import lk.anko.adapters.observable.listAdapter
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*
import java.util.*

/**
 * A [ViewGenerator] for selecting which demo you want to view.
 * Created by jivie on 5/5/16.
 */
class SelectorScreen(val main: MainScreen) : ViewGenerator {

    val demos: ArrayList<Pair<String, () -> ViewGenerator>> = arrayListOf(
            "Example Login" to {
                ExampleLoginVC({
                    main.stack.pop()
                })
            },
            "Observable List" to { ObservableListVC() },
            "Observable List 2" to { ObservableList2VC() },
            "Observable List 3" to { ObservableList3VC() },
            "Network Image" to { NetImageTestVC() },
            "Observable Property" to { ObservablePropertyTestVC() },
            "View Controller Stacks" to { StackDemoVC(main.stack) },
            "Coordinator Layout" to { CoordinatorLayoutTestVC() },
            "List from Network" to { NetworkListVC() },
            "Just a Lambda View" to {
                { access: ActivityAccess ->
                    access.anko {
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

    override fun invoke(access: ActivityAccess): View = access.anko {
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