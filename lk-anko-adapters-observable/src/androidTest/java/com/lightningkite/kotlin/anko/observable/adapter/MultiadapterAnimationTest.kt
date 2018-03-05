package com.lightningkite.kotlin.anko.observable.adapter

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import lk.anko.adapters.MultiRecyclerViewAdapter
import lk.anko.adapters.SingleRecyclerViewAdapter
import lk.anko.adapters.observable.ListRecyclerViewAdapter
import lk.anko.adapters.observable.StandardListAdapter
import lk.anko.adapters.observable.attachAnimations
import lk.kotlin.observable.list.observableListOf
import org.jetbrains.anko.view

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.assertEquals

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class MultiadapterAnimationTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val context = InstrumentationRegistry.getTargetContext()

        val list = observableListOf('a', 'b', 'c', 'd')
        val singleAdapter = SingleRecyclerViewAdapter { view() }
        val listAdapter = ListRecyclerViewAdapter(
                context = context,
                initialList = list,
                makeView = { view() }
        )
        listAdapter.attachAnimations(list)
        val multiAdapter = MultiRecyclerViewAdapter(context, listOf(singleAdapter, listAdapter))
        multiAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                println("onChanged()")
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                println("onItemRangeRemoved($positionStart, $itemCount)")
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                println("onItemRangeMoved($fromPosition, $toPosition, $itemCount)")
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                println("onItemRangeInserted($positionStart, $itemCount)")
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                println("onItemRangeChanged($positionStart, $itemCount)")
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                println("onItemRangeChanged($positionStart, $itemCount, $payload)")
            }
        })
    }
}
