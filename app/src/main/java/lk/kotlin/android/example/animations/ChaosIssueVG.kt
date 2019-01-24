package lk.kotlin.android.example.animations

import android.net.Uri
import android.view.Gravity
import android.view.View
import android.widget.TextView
import lk.android.activity.access.ActivityAccess
import lk.android.animations.AnimationSet
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.android.mighty.view.bind
import lk.anko.adapters.observable.listAdapter
import lk.anko.animations.swapView
import lk.anko.extensions.anko
import lk.anko.extensions.verticalGridRecyclerView
import lk.kotlin.observable.property.StackObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import lk.kotlin.observable.property.lifecycle.listen
import lk.kotlin.utils.lambda.invokeAll
import org.jetbrains.anko.*
import java.util.*

/**
 * This example is meant to demonstrate how to use the animation package's features.
 */
class ChaosIssueVG() : ViewGenerator {

    val stack = StackObservableProperty<ViewGenerator>({{ access -> View(access.context)}})

    override fun invoke(access: ActivityAccess): View = access.context.anko().verticalLayout {
        gravity = Gravity.CENTER
        padding = dip(8)

        swapView {
            bind(access, stack)
        }.lparams(matchParent, dip(150))


        button {
            text = "CHAOS, CHAOS"
            setOnClickListener {
                fun randomGravity(): Int {
                    return when(Math.random().times(9).toInt()){
                        0 -> Gravity.TOP or Gravity.LEFT
                        1 -> Gravity.TOP or Gravity.CENTER_HORIZONTAL
                        2 -> Gravity.TOP or Gravity.RIGHT
                        3 -> Gravity.CENTER_VERTICAL or Gravity.LEFT
                        4 -> Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
                        5 -> Gravity.CENTER_VERTICAL or Gravity.RIGHT
                        6 -> Gravity.BOTTOM or Gravity.LEFT
                        7 -> Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                        8 -> Gravity.BOTTOM or Gravity.RIGHT
                        else -> Gravity.BOTTOM or Gravity.RIGHT
                    }
                }
                fun randomViewGenerator(): ViewGenerator =  { it.context.anko().textView("CHAOS ${Math.random()}").apply { gravity = randomGravity() } }
                stack.push { it.context.anko().textView("CHAOS ONE").apply { gravity = Gravity.TOP or Gravity.LEFT } }
                stack.push { it.context.anko().textView("CHAOS TWO").apply { gravity = Gravity.BOTTOM or Gravity.LEFT } }
                stack.push { it.context.anko().textView("CHAOS THREE").apply { gravity = Gravity.TOP or Gravity.RIGHT } }
                stack.pop()
                stack.push { it.context.anko().textView("CHAOS FOUR").apply { gravity = Gravity.BOTTOM or Gravity.RIGHT } }
                stack.pop()
                stack.pop()
                stack.pop()
            }
        }.lparams(matchParent, wrapContent){ margin = dip(8) }

    }
}