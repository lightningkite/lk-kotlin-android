package lk.anko.extensions

import android.support.v7.widget.RecyclerView
import lk.android.extensions.StickyHeadersItemDecorator
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoContextImpl

/**
 * Adds sticky headers.
 * @param list A reference to the list that the sticky headers are applied to.
 * @param group A lambda that sorts the items into categories.
 * @param makeView A lambda that generates a view based on the category.
 * Created by josep on 2/11/2016.
 */
inline fun <T : Any, K> RecyclerView.stickyHeaders(
        list: List<T>,
        noinline group: (T) -> K,
        crossinline makeView: AnkoContext<Unit>.(K) -> Unit) {
    addItemDecoration(StickyHeadersItemDecorator(list, group, { AnkoContextImpl<Unit>(context, Unit, false).apply { makeView(it) }.view }))
}
