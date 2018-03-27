package lk.kotlin.android.example.animations

import android.net.Uri
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.animations.AnimationSet
import lk.android.lifecycle.lifecycle
import lk.android.mighty.view.ViewGenerator
import lk.anko.adapters.observable.listAdapter
import lk.anko.animations.transitionView
import lk.anko.extensions.anko
import lk.anko.extensions.verticalGridRecyclerView
import lk.kotlin.lifecycle.listen
import lk.kotlin.observable.property.StandardObservableProperty
import lk.kotlin.observable.property.lifecycle.bind
import lk.kotlin.utils.lambda.invokeAll
import org.jetbrains.anko.*
import java.util.*

/**
 * This example is meant to demonstrate how to use the animation package's features.
 */
class TransitionViewVG() : ViewGenerator {

    val lastPaused = StandardObservableProperty<Date?>(null)
    val lastResumed = StandardObservableProperty<Date?>(null)
    val imageUri = StandardObservableProperty<Uri?>(null)

    val transitionEvent = ArrayList<(AnimationSet) -> Unit>()

    override fun invoke(access: ActivityAccess): View = access.context.anko().verticalLayout {
        gravity = Gravity.CENTER
        padding = dip(8)

        transitionView {

            textView {
                text = "First"
                padding = dip(8)
                gravity = Gravity.CENTER
            }.lparams(matchParent, matchParent)

            textView {
                text = "Second"
                padding = dip(8)
                gravity = Gravity.CENTER
            }.lparams(matchParent, matchParent)

            textView {
                text = "Third"
                padding = dip(8)
                gravity = Gravity.CENTER
            }.lparams(matchParent, matchParent)

            textView {
                text = "Fourth"
                padding = dip(8)
                gravity = Gravity.CENTER
            }.lparams(matchParent, matchParent)

            animate(0)
            var current = 0

            lifecycle.listen(transitionEvent) { animationSet ->
                current = (current + 1) % childCount
                animate(current, animationSet)
            }

        }.lparams(matchParent, dip(150))


        verticalGridRecyclerView(2) {
            val animationSets = listOf(
                    "Fade" to AnimationSet.fade,
                    "Flip" to AnimationSet.flipVertical,
                    "Down" to AnimationSet.slideDown,
                    "Up" to AnimationSet.slideUp,
                    "Pop" to AnimationSet.slidePop,
                    "Push" to AnimationSet.slidePush
            )
            adapter = listAdapter(animationSets) { itemObs ->
                button {
                    lifecycle.bind(itemObs) {
                        text = it.first
                    }
                    setOnClickListener {
                        transitionEvent.invokeAll(itemObs.value.second)
                    }
                }.lparams(matchParent, wrapContent) { margin = dip(8) }
            }
        }.lparams(matchParent, 0, 1f)

    }
}