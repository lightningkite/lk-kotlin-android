@file:JvmName("LkKotlinObservableListLifecycle")
@file:JvmMultifileClass

package lk.kotlin.observable.list.lifecycle

import lk.kotlin.lifecycle.LifecycleConnectable
import lk.kotlin.lifecycle.LifecycleListener
import lk.kotlin.observable.list.*

/**
 * Created by joseph on 1/31/18.
 */


fun <E, G, L> ObservableList<E>.multiGroupingBy(
        lifecycle: LifecycleConnectable,
        grouper: (E) -> Collection<G>,
        listWrapper: (ObservableList<E>) -> L
): ObservableListMultiGroupingBy<E, G, L> {
    val list = ObservableListMultiGroupingBy(this, grouper, listWrapper)
    lifecycle.connect(object : LifecycleListener {
        override fun onStart() {
            list.setup()
        }

        override fun onStop() {
            list.close()
        }
    })
    return list
}

fun <E, G> ObservableList<E>.multiGroupingBy(lifecycle: LifecycleConnectable, grouper: (E) -> Collection<G>) = multiGroupingBy(lifecycle, grouper, { it })

fun <E> ObservableList<E>.sorting(lifecycle: LifecycleConnectable, sorter: (E, E) -> Boolean): ObservableListSorted<E> {
    val list = ObservableListSorted(this, sorter)
    return list
}

fun <E, G, L> ObservableList<E>.groupingBy(
        lifecycle: LifecycleConnectable,
        grouper: (E) -> G,
        listWrapper: (ObservableList<E>) -> L
): ObservableListGroupingBy<E, G, L> {
    val list = ObservableListGroupingBy(this, grouper, listWrapper)
    lifecycle.connect(object : LifecycleListener {
        override fun onStart() {
            list.setup()
        }

        override fun onStop() {
            list.close()
        }
    })
    return list
}

fun <E, G> ObservableList<E>.groupingBy(lifecycle: LifecycleConnectable, grouper: (E) -> G) = groupingBy(lifecycle, grouper, { it })

fun <S, E> ObservableList<S>.flatMapping(lifecycle: LifecycleConnectable, mapper: (S) -> ObservableList<E>): ObservableListFlatMapping<S, E> {
    val list = ObservableListFlatMapping(this, mapper)
    lifecycle.connect(object : LifecycleListener {
        override fun onStart() {
            list.setup()
        }

        override fun onStop() {
            list.close()
        }
    })
    return list
}

fun <E> ObservableList<E>.filtering(lifecycle: LifecycleConnectable): ObservableListFiltered<E> {
    val list = ObservableListFiltered(this)
    lifecycle.connect(object : LifecycleListener {
        override fun onStart() {
            list.setup()
        }

        override fun onStop() {
            list.close()
        }
    })
    return list
}

fun <E> ObservableList<E>.filtering(lifecycle: LifecycleConnectable, initFilter: (E) -> Boolean): ObservableListFiltered<E> {
    val list = ObservableListFiltered(this).apply {
        filter = initFilter
    }
    lifecycle.connect(object : LifecycleListener {
        override fun onStart() {
            list.setup()
        }

        override fun onStop() {
            list.close()
        }
    })
    return list
}