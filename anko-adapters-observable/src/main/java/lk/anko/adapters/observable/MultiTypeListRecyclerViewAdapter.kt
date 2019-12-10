@file:JvmName("LkAnkoAdaptersObservable")
@file:JvmMultifileClass

package lk.anko.adapters.observable


import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import lk.android.lifecycle.lifecycle
import lk.anko.extensions.anko
import lk.kotlin.observable.list.ObservableList
import lk.kotlin.observable.property.BaseObservableProperty
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.lifecycle.listen
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoContextImpl
import org.jetbrains.anko.frameLayout
import java.util.*
import kotlin.collections.HashMap

/**
 * An adapter for RecyclerViews intended to be used in all cases.
 *
 * Created by jivie on 5/4/16.
 */
class MultiTypeListRecyclerViewAdapter(
        val context: Context,
        initialList: List<Any>,
        val makeViews: Map<Class<*>, ViewMakeInfo<*>>
) : RecyclerView.Adapter<MultiTypeListRecyclerViewAdapter.ViewHolder<*>>() {

    class ViewHolder<T>(
            itemView: View,
            val type: Class<*>,
            val observable: ItemObservableProperty<T>
    ): RecyclerView.ViewHolder(itemView) {
        fun update(index: Int, value: Any) {
            if(type.isInstance(value)){
                observable.index = index
                @Suppress("UNCHECKED_CAST")
                observable.value = value as T
            } else {
                println("WARNING: Type of value $value is not the expected $type")
            }
        }
    }

    class ItemObservableProperty<T>(override var value: T): BaseObservableProperty<T>() {
        var index: Int = 0
    }

    class ViewMakeInfo<T>(
            val type: Class<T>,
            val default: T,
            val make: AnkoContext<*>.(ItemObservableProperty<T>)->View
    ) {
        fun makeFull(context: Context): ViewHolder<T>{
            val obs = ItemObservableProperty(default)
            return ViewHolder(
                    itemView = make.invoke(context.anko(), obs),
                    type = type,
                    observable = obs
            )
        }

    }

    class MapBuilder {
        val map = HashMap<Class<*>, ViewMakeInfo<*>>()
        fun <T: Any> make(default: T, make: AnkoContext<*>.(ItemObservableProperty<T>)->View) {
            val type = default::class.java
            map[type] = ViewMakeInfo<T>(type = type as Class<T>, default = default, make = make)
        }
    }

    var list: List<Any> = initialList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val types = makeViews.keys.toList()

    var onScrollToBottom: (() -> Unit)? = null

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return types.indexOf(list[position].javaClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        val type = types[viewType]
        val maker = makeViews[type] ?: throw IllegalStateException()
        return maker.makeFull(context)
    }

    override fun onBindViewHolder(holder: ViewHolder<*>, position: Int) {
        holder.update(position, list[position])
    }
}

/**
 * Makes a [RecyclerView.Adapter] displaying the given [list], where the type of item in the list is
 * matched up with a view generator from [makeViews].
 */
fun <T : Any> RecyclerView.multiTypeListAdapter(
        list: List<T>,
        makeViews: MultiTypeListRecyclerViewAdapter.MapBuilder.() -> Unit
): MultiTypeListRecyclerViewAdapter {
    val newAdapter = MultiTypeListRecyclerViewAdapter(context, list, MultiTypeListRecyclerViewAdapter.MapBuilder().apply(makeViews).map)
    return newAdapter
}

/**
 * Makes a [RecyclerView.Adapter] displaying the given [list], where the type of item in the list is
 * matched up with a view generator from [makeViews].
 * Animates to show updates to the list.
 */
fun <T : Any> RecyclerView.multiTypeListAdapter(
        list: ObservableList<T>,
        makeViews: MultiTypeListRecyclerViewAdapter.MapBuilder.() -> Unit
): MultiTypeListRecyclerViewAdapter {
    val newAdapter = MultiTypeListRecyclerViewAdapter(context, list, MultiTypeListRecyclerViewAdapter.MapBuilder().apply(makeViews).map)
    newAdapter.attachAnimations(lifecycle, list, this)
    return newAdapter
}

/**
 * Makes a [RecyclerView.Adapter] displaying the given [listObs], where the type of item in the list is
 * matched up with a view generator from [makeViews].
 * Animates to show updates to the list.
 */
fun <T : Any> RecyclerView.multiTypeListAdapterObservable(
        listObs: ObservableProperty<List<T>>,
        makeViews: MultiTypeListRecyclerViewAdapter.MapBuilder.() -> Unit
): MultiTypeListRecyclerViewAdapter {
    val newAdapter = MultiTypeListRecyclerViewAdapter(context, listObs.value, MultiTypeListRecyclerViewAdapter.MapBuilder().apply(makeViews).map)
    lifecycle.listen(listObs) {
        newAdapter.list = it
        if (it is ObservableList<T>) {
            newAdapter.attachAnimations(it, this)
        }
    }
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View?) {
            val list = listObs.value
            if (list is ObservableList<T>) {
                newAdapter.attachAnimations(list, this@multiTypeListAdapterObservable)
            }
        }

        override fun onViewDetachedFromWindow(v: View?) {
            newAdapter.detatchAnimations<T, MultiTypeListRecyclerViewAdapter.ViewHolder<*>>(this@multiTypeListAdapterObservable)
        }

    })
    return newAdapter
}
