package lk.anko.extensions

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewManager
import com.lightningkite.lk_anko_extensions.R
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.layoutInflater

/**
 * Various extensions for [RecyclerView]
 * Created by joseph on 3/3/2016.
 */

inline fun ViewManager.verticalRecyclerView(init: RecyclerView.() -> Unit) = ankoView({
    val view = it.layoutInflater.inflate(R.layout.vertical_recycler_view, null) as RecyclerView
    view.apply {
        layoutManager = LinearLayoutManager(it).apply {
            this.orientation = LinearLayoutManager.VERTICAL
        }
    }
}, 0, init)

inline fun ViewManager.horizontalRecyclerView(init: RecyclerView.() -> Unit) = ankoView({
    val view = it.layoutInflater.inflate(R.layout.horizontal_recycler_view, null) as RecyclerView
    view.apply {
        layoutManager = LinearLayoutManager(it).apply {
            this.orientation = LinearLayoutManager.HORIZONTAL
        }
    }
}, 0, init)

inline fun ViewManager.verticalGridRecyclerView(spanCount: Int, init: RecyclerView.() -> Unit) = ankoView({
    val view = it.layoutInflater.inflate(R.layout.vertical_recycler_view, null) as RecyclerView
    view.apply {
        layoutManager = GridLayoutManager(it, spanCount).apply {
            this.orientation = GridLayoutManager.VERTICAL
        }
    }
}, 0, init)

inline fun ViewManager.horizontalGridRecyclerView(spanCount: Int, init: RecyclerView.() -> Unit) = ankoView({
    val view = it.layoutInflater.inflate(R.layout.horizontal_recycler_view, null) as RecyclerView
    view.apply {
        layoutManager = GridLayoutManager(it, spanCount).apply {
            this.orientation = GridLayoutManager.HORIZONTAL
        }
    }
}, 0, init)