package lk.kotlin.android.example.random

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Parcelable
import android.util.SparseArray
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.extensions.horizontalDivider
import lk.android.extensions.selectableItemBackgroundResource
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.adapters.observable.listAdapter
import lk.anko.adapters.swipeToDismiss
import lk.anko.extensions.anko
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.jvm.utils.random.random
import lk.kotlin.observable.list.ObservableListWrapper
import lk.kotlin.observable.list.sorting
import lk.kotlin.observable.property.StackObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import lk.kotlin.observable.property.lifecycle.openCloseBinding
import org.jetbrains.anko.*

/**
 * A [AnkoViewController] used for demonstrating observable lists.
 * Created by jivie on 2/10/16.
 */
class ObservableList2VG(
        val stack: StackObservableProperty<ViewGenerator>
) : ViewGenerator {

    val items = ObservableListWrapper((190 downTo 1).map { it.toString() }.toMutableList())
    val sorted = items.sorting { a, b -> a < b }

    val state = SparseArray<Parcelable>()

    override fun invoke(access: ActivityAccess): View = access.context.anko().run {
        verticalLayout {
            gravity = Gravity.CENTER

            verticalRecyclerView {

                retainState(state)

                adapter = listAdapter(sorted) { obs ->
                    textView {
                        lifecycle.bind(obs) {
                            text = it
                        }
                        gravity = Gravity.CENTER
                        textSize = 18f
                        minimumHeight = dip(40)
                        backgroundResource = selectableItemBackgroundResource
                        setOnLongClickListener {
                            items.removeAt(obs.position)
                            true
                        }
                        setOnClickListener {
                            val item = obs.value
                            stack.push {
                                it.context.anko().textView {
                                    gravity = Gravity.CENTER
                                    text = "I am item $item"
                                }
                            }
                        }
                    }.lparams(matchParent, wrapContent)
                }

                swipeToDismiss {
                    items.removeAt(it)
                }

                horizontalDivider(ColorDrawable(Color.LTGRAY))

            }.lparams(matchParent, 0, 1f)

            button("Add") {
                setOnClickListener { it: View? ->
                    items.add(Math.random().times(190).plus(1).toInt().toString() + " N")
                }
            }.lparams(matchParent, wrapContent)

            button("Update Random") {
                setOnClickListener { it: View? ->
                    val index = items.indices.random()
                    items[index] = items[index] + " M"
                }
            }.lparams(matchParent, wrapContent)

        }
    }
}

@SuppressLint("ResourceType")
fun View.retainState(repository: SparseArray<Parcelable>) {
    id = 39283
    lifecycle.openCloseBinding(
            onOpen = {
                this.restoreHierarchyState(repository)
            },
            onClose = {
                this.saveHierarchyState(repository)
            }
    )
}