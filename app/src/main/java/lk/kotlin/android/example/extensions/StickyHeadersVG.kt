package lk.kotlin.android.example.extensions

import android.graphics.Color
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.adapters.observable.listAdapter
import lk.anko.extensions.anko
import lk.anko.extensions.stickyHeaders
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

class StickyHeadersVG() : ViewGenerator {

    override fun invoke(access: ActivityAccess): View = access.context.anko().verticalRecyclerView {
        val list = (1920..2020).toList()

        adapter = listAdapter(list) { itemObs ->
            textView {
                padding = dip(8)
                lifecycle.bind(itemObs) { text = it.toString() }
            }.lparams(matchParent, wrapContent)
        }

        stickyHeaders(
                list = list,
                group = { item -> item / 10 },
                makeView = { group ->
                    textView {
                        padding = dip(8)
                        backgroundColor = Color.BLUE
                        textColor = Color.WHITE
                        text = group.toString() + "0s"
                    }
                }
        )
    }
}