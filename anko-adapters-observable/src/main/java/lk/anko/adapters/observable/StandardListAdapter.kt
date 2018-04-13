@file:JvmName("LkAnkoAdaptersObservable")
@file:JvmMultifileClass

package lk.anko.adapters.observable



import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import lk.android.lifecycle.lifecycle
import lk.kotlin.observable.list.ObservableList
import lk.kotlin.observable.property.MutableObservableProperty
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import lk.kotlin.observable.property.lifecycle.listen
import org.jetbrains.anko.AnkoContextImpl
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.wrapContent
import java.util.*

/**
 * A [BaseAdapter] made more convenient.
 * Created by josep on 1/24/2016.
 */
open class StandardListAdapter<T>(
        val context: Context,
        list: List<T>,
        val makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit
) : BaseAdapter() {

    class ItemObservable<T>(init: T) : StandardObservableProperty<T>(init) {
        var index: Int = 0
        override fun remove(element: (T) -> Unit): Boolean {
            return super.remove(element)
        }
    }

    var list: List<T> = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount(): Int = list.size
    override fun getItem(position: Int): Any? = list[position]
    override fun getItemId(position: Int): Long = position.toLong()

    @Suppress("UNCHECKED_CAST")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val item = list[position]
        if (convertView == null) {
            val newObs = ItemObservable(list[position])
            val newView = SLVAContext(this, context).apply { makeView(newObs) }.view
            newView.tag = newObs
            newObs.index = position
            newView.layoutParams = AbsListView.LayoutParams(matchParent, wrapContent)
            return newView
        } else {
            val obs = convertView.tag as ItemObservable<T>
            obs.index = position
            obs.value = (list[position])
            return convertView
        }
    }

    class SLVAContext<T>(adapter: StandardListAdapter<T>, context: Context) : AnkoContextImpl<StandardListAdapter<T>>(context, adapter, false) {
        fun <V : View> V.lparams(
                width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
                height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                init: AbsListView.LayoutParams.() -> Unit = {}
        ): V {
            val layoutParams = AbsListView.LayoutParams(width, height)
            layoutParams.init()
            this@lparams.layoutParams = layoutParams

            return this
        }
    }
}

/**
 * Sets an adapter that displays the current values in [list].
 */
fun <T> Spinner.standardAdapter(
        list: List<T>,
        makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit
): StandardListAdapter<T> {
    val newAdapter = StandardListAdapter(context, list, makeView)
    adapter = newAdapter
    return newAdapter
}

/**
 * Sets an adapter that displays the values in [listObs], updating when it changes.
 */
fun <T> Spinner.standardAdapter(
        listObs: ObservableProperty<List<T>>,
        makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit
): StandardListAdapter<T> {
    val newAdapter: StandardListAdapter<T> = StandardListAdapter(context, listObs.value, makeView)
    adapter = newAdapter
    lifecycle.listen(listObs) {
        newAdapter.list = it
    }
    return newAdapter
}

/**
 * Sets an adapter that displays the values in [list].
 */
fun <T> Spinner.standardAdapter(
        list: ObservableList<T>,
        makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit
): StandardListAdapter<T> {
    val newAdapter: StandardListAdapter<T> = StandardListAdapter(context, list, makeView)
    adapter = newAdapter
    lifecycle.listen(list.onUpdate) {
        newAdapter.notifyDataSetChanged()
    }
    return newAdapter
}

/**
 * Sets an adapter that displays the values in [list].
 * When the user selects an object, it will be updated into [selected].
 * If [selected] is updated externally, the view will update accordingly.
 */
fun <T> Spinner.standardAdapter(
        list: ObservableList<T>,
        selected: MutableObservableProperty<T>,
        makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit
): StandardListAdapter<T> {
    val newAdapter: StandardListAdapter<T> = StandardListAdapter(context, list, makeView)
    adapter = newAdapter

    var indexAlreadySet = false

    lifecycle.listen(list.onUpdate) {
        newAdapter.notifyDataSetChanged()
        val index = list.indexOf(selected.value)
        println("update to $index - ${selected.value}")
        if (index == -1) {
            println("could not find ${selected.value}")
            setSelection(0)
            return@listen
        }
        setSelection(index)
    }

    lifecycle.bind(selected) { it ->
        val index = list.indexOf(it)
        println("selected to $index - $it")
        if (index == -1) {
            println("could not find ${it?.hashCode()} in ${list.joinToString { it?.hashCode().toString() }}")
            setSelection(0)
            return@bind
        }
        if (!indexAlreadySet) {
            setSelection(index)
        } else {
            indexAlreadySet = false
        }
    }

    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            println("set to $position - ${list[position]}")
            indexAlreadySet = true
            selected.value = (list[position])
        }
    }
    return newAdapter
}


/**
 * Sets an adapter that displays the values in [list].
 * When the user selects an object, it will be updated into [selected].
 * If [selected] is updated externally, the view will update accordingly.
 */
fun <T> Spinner.standardAdapter(
        list: List<T>,
        selected: MutableObservableProperty<T>,
        makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit
): StandardListAdapter<T> {
    val newAdapter: StandardListAdapter<T> = StandardListAdapter(context, list, makeView)
    adapter = newAdapter

    var indexAlreadySet = false

    lifecycle.bind(selected) { it ->
        val index = list.indexOf(it)
        println("selected to $index - $it")
        if (index == -1) {
            println("could not find $it")
            setSelection(0)
            return@bind
        }
        if (!indexAlreadySet) {
            setSelection(index)
        } else {
            indexAlreadySet = false
        }
    }

    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            println("set to $position - ${list[position]}")
            indexAlreadySet = true
            selected.value = (list[position])
        }
    }
    return newAdapter
}

@Suppress("UNCHECKED_CAST")
class FilterableStandardListAdapter<T>(
        context: Context,
        val fullList: List<T>,
        val convertToString: (T) -> String = { it.toString() },
        val matches: T.(CharSequence) -> Boolean = { convertToString(this).contains(it, true) },
        makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit,
        val mutableList: MutableList<T> = mutableListOf<T>()
) : StandardListAdapter<T>(context, mutableList, makeView), Filterable {

    override fun getFilter(): Filter? = object : Filter() {
        private val suggestions: ArrayList<T> = ArrayList()
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            if (constraint == null) return FilterResults()
            suggestions.clear()
            val constraintString = constraint.toString()
            for (item in fullList) {
                if (item.matches(constraintString)) {
                    suggestions.add(item)
                }
            }
            val results = FilterResults()
            results.count = suggestions.size
            results.values = suggestions
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            if (results.count > 0) {
                //list = results.values as ArrayList<T>
                mutableList.clear()
                mutableList.addAll(results.values as ArrayList<T>)
                notifyDataSetChanged()
            }
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return convertToString(resultValue as T)
        }
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? = getView(position, convertView, parent)
}


/**
 * Sets an adapter that displays the values in [list].
 */
fun <T> AutoCompleteTextView.standardAdapter(
        list: List<T>,
        convertToString: (T) -> String = { it.toString() },
        makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit
): FilterableStandardListAdapter<T> {
    val newAdapter = FilterableStandardListAdapter(context, list, convertToString, makeView = makeView)
    setAdapter(newAdapter)
    return newAdapter
}

/**
 * Sets an adapter that displays the values in [listObs].
 */
fun <T> AutoCompleteTextView.standardAdapter(
        listObs: ObservableProperty<List<T>>,
        convertToString: (T) -> String = { it.toString() },
        makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit
): FilterableStandardListAdapter<T> {
    val newAdapter = FilterableStandardListAdapter(context, listObs.value, convertToString, makeView = makeView)
    setAdapter(newAdapter)
    lifecycle.listen(listObs) {
        newAdapter.list = it
    }
    return newAdapter
}

/**
 * Sets an adapter that displays the values in [list].
 */
fun <T> AutoCompleteTextView.standardAdapter(
        list: ObservableList<T>,
        convertToString: (T) -> String = { it.toString() },
        makeView: StandardListAdapter.SLVAContext<T>.(StandardListAdapter.ItemObservable<T>) -> Unit
): FilterableStandardListAdapter<T> {
    val newAdapter = FilterableStandardListAdapter(context, list, convertToString, makeView = makeView)
    setAdapter(newAdapter)
    lifecycle.listen(list.onUpdate) {
        newAdapter.notifyDataSetChanged()
    }
    return newAdapter
}