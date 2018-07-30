package lk.kotlin.android.example.random

import android.support.transition.AutoTransition
import android.support.transition.Scene
import android.support.transition.TransitionManager
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.extensions.anko
import lk.kotlin.android.example.styleDefault
import lk.kotlin.observable.property.MutableObservableProperty

import lk.kotlin.observable.property.StackObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import org.jetbrains.anko.*

/**
 * Created as a dummy VC to test out the stack.
 * Created by josep on 11/6/2015.
 */
class TransitionVG() : ViewGenerator {

    companion object {
        fun genId(num:Int) = num + 0xFFF
    }

    class SubA(val current: MutableObservableProperty<ViewGenerator>): ViewGenerator {
        override fun invoke(access: ActivityAccess): View = access.context.anko().verticalLayout {
            gravity = Gravity.CENTER
                id = genId(0)

            textView {
                styleDefault()
                id = genId(1)
                text = "Here is one possible layout: A!"
            }.lparams(matchParent, wrapContent) { margin = dip(8) }

            button {
                styleDefault()
                id = genId(2)
                text = "Go to B"
                setOnClickListener {
                    current.value = SubB(current)
                }
            }.lparams(matchParent, wrapContent) { margin = dip(8) }
        }
    }

    class SubB(val current: MutableObservableProperty<ViewGenerator>): ViewGenerator {
        override fun invoke(access: ActivityAccess): View = access.context.anko().linearLayout {
            gravity = Gravity.CENTER
                id = genId(0)

            textView {
                styleDefault()
                id = genId(1)
                text = "Here is one possible layout: B!"
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }

            button {
                styleDefault()
                id = genId(2)
                text = "Go to A"
                setOnClickListener {
                    current.value = SubA(current)
                }
            }.lparams(wrapContent, wrapContent) { margin = dip(8) }
        }
    }

    val current = StandardObservableProperty<ViewGenerator>({View(it.context)})
    init{
        current.value = SubA(this@TransitionVG.current)
    }

    override fun invoke(access: ActivityAccess): View = access.context.anko().verticalLayout {
        gravity = Gravity.CENTER

        textView {
            styleDefault()
            text = "This is a test of magical Android transitions."
        }.lparams(matchParent, wrapContent) { margin = dip(8) }

        frameLayout {

            lifecycle.bind(current){
                TransitionManager.go(Scene(this, it.invoke(access)), AutoTransition())
            }

        }.lparams(matchParent, 0, 1f)
    }
}