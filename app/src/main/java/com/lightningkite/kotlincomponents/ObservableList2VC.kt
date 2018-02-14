package com.lightningkite.kotlincomponents

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.activity.access.ViewGenerator
import lk.android.extensions.horizontalDivider
import lk.android.extensions.selectableItemBackgroundResource
import lk.android.lifecycle.lifecycle
import lk.anko.activity.access.anko
import lk.anko.adapters.observable.listAdapter
import lk.anko.adapters.swipeToDismiss
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.jvm.utils.range.random
import lk.kotlin.observable.list.ObservableListWrapper
import lk.kotlin.observable.list.sorting
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

/**
 * A [AnkoViewController] used for demonstrating observable lists.
 * Created by jivie on 2/10/16.
 */
class ObservableList2VC() : ViewGenerator {

    val items = ObservableListWrapper((190 downTo 1).map { it.toString() }.toMutableList())
    val sorted = items.sorting { a, b -> a < b }

    override fun toString(): String = "List Test"

    override fun invoke(access: ActivityAccess): View = access.anko {
        verticalLayout {
            gravity = Gravity.CENTER

            verticalRecyclerView {

                adapter = listAdapter(sorted) { obs ->
                    textView {
                        lifecycle.bind(obs) {
                            text = it
                        }
//                    newLifecycle.bind(obs){
//                        text = it
//                    }
//                    obs += { text = it }
                        gravity = Gravity.CENTER
                        textSize = 18f
                        minimumHeight = dip(40)
                        backgroundResource = selectableItemBackgroundResource
                        setOnLongClickListener {
                            items.removeAt(obs.position)
                            true
                        }
                    }.lparams(matchParent, wrapContent)
                }

                swipeToDismiss {
                    items.removeAt(it)
                }

                horizontalDivider(ColorDrawable(Color.LTGRAY))

            }.lparams(matchParent, 0, 1f)

            button("Add") {
                setOnClickListener { it: View? ->
                    items.add(Math.random().times(190).plus(1).toInt().toString() + " N")
                }
            }.lparams(matchParent, wrapContent)

            button("Update Random") {
                setOnClickListener { it: View? ->
                    val index = items.indices.random()
                    items[index] = items[index] + " M"
                }
            }.lparams(matchParent, wrapContent)

        }
    }
}