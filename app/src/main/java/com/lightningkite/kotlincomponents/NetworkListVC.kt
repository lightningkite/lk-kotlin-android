package com.lightningkite.kotlincomponents

import android.graphics.Color
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.android.ui.thread.UIThread
import lk.anko.adapters.observable.listAdapter
import lk.anko.animations.observable.progressLayout
import lk.anko.extensions.anko
import lk.anko.extensions.verticalRecyclerView
import lk.kotlin.jvm.utils.async.Async
import lk.kotlin.jvm.utils.async.invokeOn
import lk.kotlin.jvm.utils.async.thenOn
import lk.kotlin.observable.list.observableListOf
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Demonstrates loading and displaying a list from the network.
 * Created by josep on 11/10/2016.
 */
class NetworkListVC : ViewGenerator {

    override fun toString(): String {
        return "Network List Example"
    }

    val posts = observableListOf<Post>()

    init {
        ExampleAPI.getPosts().thenOn(UIThread) {
            if (it.isSuccessful()) {
                posts.replace(it.result!!)
            } else {
                posts.add(Post(title = "Loading error", body = it.errorString ?: "Unknown error"))
            }
        }.invokeOn(Async)
    }

    override fun invoke(access: ActivityAccess): View = access.context.anko().verticalLayout {

        textView("This data is from https://jsonplaceholder.typicode.com/.") {
            styleDefault()
        }.lparams(matchParent, wrapContent) { margin = dip(8) }

        progressLayout { runningObs ->

            lifecycle.bind(posts.onUpdate) { posts ->
                runningObs.value = posts.isEmpty()
            }

            verticalRecyclerView {
                adapter = listAdapter(posts) { itemObs ->
                    cardView {
                        backgroundColor = Color.WHITE

                        verticalLayout {
                            padding = dip(8)

                            textView {
                                styleInvertedTitle()
                                lifecycle.bind(itemObs) {
                                    text = it.title
                                }
                            }.lparams(matchParent, wrapContent) { margin = dip(8) }

                            textView {
                                styleInverted()
                                lifecycle.bind(itemObs) {
                                    text = it.body
                                }
                            }.lparams(matchParent, wrapContent) { margin = dip(8) }

                        }
                    }.lparams(matchParent, wrapContent) { margin = dip(8) }
                }
            }
        }.lparams(matchParent, 0, 1f)

    }
}