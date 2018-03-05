package com.lightningkite.kotlincomponents

import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.mighty.view.ViewGenerator
import lk.anko.activity.access.anko
import lk.kotlin.observable.property.StackObservableProperty
import org.jetbrains.anko.button
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

/**
 * Created as a dummy VC to test out the stack.
 * Created by josep on 11/6/2015.
 */
class StackDemoVC(val stack: StackObservableProperty<ViewGenerator>, val depth: Int = 1) : ViewGenerator {

    override fun toString(): String = "Stack Demo ($depth)"

    override fun invoke(access: ActivityAccess): View = access.anko {
        verticalLayout {
            gravity = Gravity.CENTER

            textView("This view controller has a depth of $depth.") {
                styleDefault()
            }

            button("Go deeper") {
                styleDefault()
                setOnClickListener { it: View? ->
                    stack.push(StackDemoVC(stack, depth + 1))
                }
            }

            button("Go back") {
                styleDefault()
                setOnClickListener { it: View? ->
                    stack.pop()
                }
            }
        }
    }
}