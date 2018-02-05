package com.lightningkite.kotlincomponents

import android.view.Gravity
import android.view.View
import com.lightningkite.kotlin.anko.activity.ActivityAccess
import com.lightningkite.kotlin.anko.activity.ViewGenerator
import com.lightningkite.kotlin.anko.activity.anko
import com.lightningkite.kotlin.anko.lifecycle
import com.lightningkite.kotlin.anko.observable.adapter.listAdapter
import com.lightningkite.kotlin.anko.selectableItemBackgroundResource
import com.lightningkite.kotlin.anko.verticalRecyclerView
import com.lightningkite.kotlin.observable.property.bind
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