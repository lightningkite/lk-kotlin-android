package com.lightningkite.kotlincomponents

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.mighty.view.ViewGenerator
import lk.android.extensions.getColorCompat
import lk.android.extensions.horizontalDivider
import lk.android.extensions.selectableItemBackgroundResource
import lk.android.lifecycle.lifecycle
import lk.android.ui.thread.UIThread
import lk.anko.activity.access.anko
import lk.anko.adapters.multiAdapter
import lk.anko.adapters.observable.listAdapter
import lk.anko.adapters.singleAdapter
import lk.anko.adapters.swipeToDismiss
import lk.anko.extensions.stickyHeaders
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.observable.list.ObservableListWrapper
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

/**
 * A [AnkoViewController] used for demonstrating observable lists.
 * Created by jivie on 2/10/16.
 */
class ObservableList3VC() : ViewGenerator {

    val items = ObservableListWrapper(arrayListOf<String>("0", "2", "5", "9", "d"))

    override fun toString(): String = "List Test"

    override fun invoke(access: ActivityAccess): View = access.anko {
        verticalLayout {
            gravity = Gravity.CENTER

            verticalRecyclerView {

                adapter = multiAdapter(
                        singleAdapter {
                            textView {
                                gravity = Gravity.CENTER
                                textSize = 18f
                                minimumHeight = dip(40)
                                backgroundResource = selectableItemBackgroundResource
                                text = "Header"
                            }
                        },
                        listAdapter(items) { obs ->
                            textView {
                                lifecycle.bind(obs, items.onUpdate) { it, itemsRaw ->
                                    UIThread.execute{
                                        text = it + " (position: ${obs.position})"
                                    }
                                }
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
                )

                horizontalDivider(ColorDrawable(Color.LTGRAY))

            }.lparams(matchParent, 0, 1f)

            button("Add at 2") {
                setOnClickListener { it: View? ->
                    items.add(2, Math.random().times(10).plus(1).toInt().toString())
                }
            }.lparams(matchParent, wrapContent)

        }
    }
}