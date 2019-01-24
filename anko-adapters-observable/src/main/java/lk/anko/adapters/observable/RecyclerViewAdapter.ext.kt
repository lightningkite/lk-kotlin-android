@file:JvmName("LkAnkoAdaptersObservable")
@file:JvmMultifileClass

package lk.anko.adapters.observable



import android.support.v7.widget.RecyclerView
import android.view.View
import com.lightningkite.lk_android_lifecycle.R
import lk.kotlin.observable.list.ObservableList
import lk.kotlin.observable.list.ObservableListListenerSet
import lk.kotlin.observable.list.addListenerSet
import lk.kotlin.observable.list.removeListenerSet
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.lifecycle.Lifecycle
import lk.kotlin.observable.property.lifecycle.openCloseBinding
import java.util.*

/**
 * Tools for attaching animations, used internally mostly.
 * Created by joseph on 9/20/16.
 */

@Suppress("UNCHECKED_CAST")
private var View.previousListenerSet: Pair<ObservableList<*>, ObservableListListenerSet<*>>?
    get() = getTag(R.id.recyclerview_listener) as? Pair<ObservableList<*>, ObservableListListenerSet<*>>
    set(value){
        setTag(R.id.recyclerview_listener, value)
    }

/**
 * Attaches updates from an [ObservableList] to the adapter, such that the UI will display changes
 * in the adapter.
 */
fun <ITEM, VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.attachAnimations(list: ObservableList<ITEM>, tagView: View) {
    detatchAnimations<ITEM, VH>(tagView)
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
    tagView.previousListenerSet = newSet
    list.addListenerSet(newSet.second)
}

/**
 * Detaches updates made by [attachAnimations].
 */
@Suppress("UNCHECKED_CAST")
fun <ITEM, VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.detatchAnimations(tagView: View) {
    val prev = tagView.previousListenerSet
    if (prev != null) {
        (prev.first as ObservableList<ITEM>).removeListenerSet(prev.second as ObservableListListenerSet<ITEM>)
        tagView.previousListenerSet = null
    }
}

/**
 * Attaches updates from an [ObservableList] to the adapter for the duration of the lifecycle.
 */
fun <ITEM, VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.attachAnimations(lifecycle: Lifecycle, list: ObservableList<ITEM>, tagView: View) {
    lifecycle.openCloseBinding(
            onOpen = {
                notifyDataSetChanged()
                attachAnimations<ITEM, VH>(list, tagView)
            },
            onClose = {
                detatchAnimations<ITEM, VH>(tagView)
            }
    )
}