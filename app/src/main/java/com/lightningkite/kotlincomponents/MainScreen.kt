package com.lightningkite.kotlincomponents

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.mighty.view.ViewGenerator
import lk.android.extensions.elevationCompat
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.bind
import lk.anko.activity.access.anko
import lk.anko.adapters.MultiRecyclerViewAdapter
import lk.anko.adapters.SingleRecyclerViewAdapter
import lk.anko.adapters.observable.ListRecyclerViewAdapter
import lk.anko.adapters.observable.attachAnimations
import lk.anko.animations.swapView
import lk.kotlin.lifecycle.listen
import lk.kotlin.observable.list.observableListOf
import lk.kotlin.observable.property.StackObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.actionMenuView
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout

/**
 * A main view controller.
 *
 * Controls an appcompat toolbar, and you need to attach it to a stack.
 *
 * This methodology is up for refactoring, so this may not be permanent.
 *
 * Created by jivie on 2/11/16.
 */
class MainScreen() : ViewGenerator {
    val stack = StackObservableProperty<ViewGenerator>().apply {
        push(SelectorScreen(this@MainScreen))
    }

    override fun invoke(access: ActivityAccess): View = access.anko {

//        //test
//        run{
//            val context = this.ctx
//
//            val list = observableListOf('a', 'b', 'c', 'd')
//            val singleAdapter = SingleRecyclerViewAdapter { view() }
//            val listAdapter = ListRecyclerViewAdapter(
//                    context = context,
//                    initialList = list,
//                    makeView = { view() }
//            )
//            listAdapter.attachAnimations(list)
//            val multiAdapter = MultiRecyclerViewAdapter(context, listOf(singleAdapter, listAdapter))
//            multiAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
//                override fun onChanged() {
//                    println("onChanged()")
//                }
//
//                override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
//                    println("onItemRangeRemoved($positionStart, $itemCount)")
//                }
//
//                override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
//                    println("onItemRangeMoved($fromPosition, $toPosition, $itemCount)")
//                }
//
//                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
//                    println("onItemRangeInserted($positionStart, $itemCount)")
//                }
//
//                override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
//                    println("onItemRangeChanged($positionStart, $itemCount)")
//                }
//
//                override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
//                    println("onItemRangeChanged($positionStart, $itemCount, $payload)")
//                }
//            })
//            list.add('e')
//            list.remove('c')
//            println("Success")
//        }

        verticalLayout {

            lifecycle.listen(access.onBackPressed) { stack.popOrFalse() }

            toolbar {
                elevationCompat = dip(4).toFloat()
                actionMenuView {
                }.lparams(Gravity.RIGHT)

                lifecycle.bind(stack) {
                    this.title = it.toString()
                    setNavigationOnClickListener { stack.pop() }
                    if (stack.stack.size > 1) {
                        setNavigationIcon(R.drawable.ic_back)
                    } else {
                        navigationIcon = null
                    }
                }

            }.lparams(matchParent, wrapContent)

            coordinatorLayout {
                swapView { bind(access, stack) }.lparams(matchParent, matchParent)
            }.lparams(matchParent, 0, 1f)
        }
    }
}