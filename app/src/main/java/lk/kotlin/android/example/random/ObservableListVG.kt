package lk.kotlin.android.example.random

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.extensions.getColorCompat
import lk.android.extensions.horizontalDivider
import lk.android.extensions.selectableItemBackgroundResource
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.adapters.observable.listAdapter
import lk.anko.adapters.swipeToDismiss
import lk.anko.extensions.anko
import lk.anko.extensions.stickyHeaders
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.android.example.R
import lk.kotlin.observable.list.ObservableListWrapper
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

/**
 * A [AnkoViewController] used for demonstrating observable lists.
 * Created by jivie on 2/10/16.
 */
class ObservableListVG() : ViewGenerator {

    val items = ObservableListWrapper(arrayListOf<String>("A", "B", "C"))

    override fun invoke(access: ActivityAccess): View = access.context.anko().run {
        verticalLayout {
            gravity = Gravity.CENTER

            verticalRecyclerView {

                adapter = listAdapter(items) { obs ->
                    textView {
                        lifecycle.bind(obs) {
                            text = it + " (position: ${obs.position})"
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

                stickyHeaders(items, {
                    when (it.toFloatOrNull()) {
                        null -> "Not a Number"
                        in 0..5 -> "Low"
                        else -> "High"
                    }
                }, {
                    textView {
                        text = it
                        backgroundColor = resources.getColorCompat(R.color.colorPrimary)
                        padding = dip(8)
                    }
                })

                swipeToDismiss {
                    items.removeAt(it)
                }

                horizontalDivider(ColorDrawable(Color.LTGRAY), 1)

            }.lparams(matchParent, 0, 1f)

            button("Add") {
                setOnClickListener { it: View? ->
                    items.add(Math.random().times(10).plus(1).toInt().toString())
                }
            }.lparams(matchParent, wrapContent)

        }
    }
}