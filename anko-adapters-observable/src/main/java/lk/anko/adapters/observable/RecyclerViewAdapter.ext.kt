@file:JvmName("LkAnkoAdaptersObservable")
@file:JvmMultifileClass

package lk.anko.adapters.observable



import android.support.v7.widget.RecyclerView
import lk.kotlin.observable.list.ObservableList
import lk.kotlin.observable.list.ObservableListListenerSet
import lk.kotlin.observable.list.addListenerSet
import lk.kotlin.observable.list.removeListenerSet
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.lifecycle.openCloseBinding
import java.util.*

/**
 * Tools for attaching animations, used internally mostly.
 * Created by joseph on 9/20/16.
 */

private val previousListenerSet: WeakHashMap<RecyclerView.Adapter<*>, Pair<ObservableList<*>, ObservableListListenerSet<*>>> = WeakHashMap()

/**
 * Attaches updates from an [ObservableList] to the adapter, such that the UI will display changes
 * in the adapter.
 */
fun <ITEM, VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.attachAnimations(list: ObservableList<ITEM>) {
    detatchAnimations<ITEM, VH>()
    val newSet = list to ObservableListListenerSet(
            onAddListener = { item: ITEM, position: Int ->
                notifyItemInserted(position)
            },
            onRemoveListener = { item: ITEM, position: Int ->
                notifyItemRemoved(position)
            },
            onChangeListener = { oldItem: ITEM, item: ITEM, position: Int ->
                notifyItemChanged(position)
            },
            onMoveListener = { item: ITEM, oldPosition: Int, position: Int ->
                notifyItemMoved(oldPosition, position)
            },
            onReplaceListener = { list: ObservableList<ITEM> ->
                notifyDataSetChanged()
            }
    )
    previousListenerSet[this] = newSet
    list.addListenerSet(newSet.second)
}

/**
 * Detaches updates made by [attachAnimations].
 */
@Suppress("UNCHECKED_CAST")
fun <ITEM, VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.detatchAnimations() {
    val prev = previousListenerSet[this]
    if (prev != null) {
        (prev.first as ObservableList<ITEM>).removeListenerSet(prev.second as ObservableListListenerSet<ITEM>)
        previousListenerSet.remove(this)
    }
}

/**
 * Attaches updates from an [ObservableList] to the adapter for the duration of the lifecycle.
 */
fun <ITEM, VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.attachAnimations(lifecycle: ObservableProperty<Boolean>, list: ObservableList<ITEM>) {
    lifecycle.openCloseBinding(
            onOpen = {
                notifyDataSetChanged()
                attachAnimations<ITEM, VH>(list)
            },
            onClose = {
                detatchAnimations<ITEM, VH>()
            }
    )
}