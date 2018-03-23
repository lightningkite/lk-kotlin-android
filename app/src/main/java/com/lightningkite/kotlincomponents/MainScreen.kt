package com.lightningkite.kotlincomponents

import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.extensions.elevationCompat
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.android.mighty.view.bind
import lk.anko.animations.swapView
import lk.anko.extensions.anko
import lk.kotlin.lifecycle.listen
import lk.kotlin.observable.property.StackObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.appcompat.v7.actionMenuView
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

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

    override fun invoke(access: ActivityAccess): View = access.context.anko().verticalLayout {

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