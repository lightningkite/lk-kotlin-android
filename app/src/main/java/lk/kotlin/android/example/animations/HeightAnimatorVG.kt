package lk.kotlin.android.example.animations

import android.net.Uri
import android.view.Gravity
import android.view.View
import lk.android.activity.access.ActivityAccess
import lk.android.animations.heightAnimator
import lk.android.mighty.view.ViewGenerator
import lk.anko.extensions.anko
import lk.kotlin.observable.property.StandardObservableProperty
import org.jetbrains.anko.*
import java.util.*

/**
 * This example is meant to demonstrate how to use the animation package's features.
 */
class HeightAnimatorVG() : ViewGenerator {

    val lastPaused = StandardObservableProperty<Date?>(null)
    val lastResumed = StandardObservableProperty<Date?>(null)
    val imageUri = StandardObservableProperty<Uri?>(null)

    override fun invoke(access: ActivityAccess): View = access.context.anko().scrollView {

        isFillViewport = true

        verticalLayout {
            gravity = Gravity.CENTER
            padding = dip(8)

            button {
                text = "Make me tall!"
                var tall = false
                setOnClickListener {
                    tall = !tall
                    if (tall) {
                        heightAnimator(dip(300)).start()
                        text = "Make me short!"
                    } else {
                        heightAnimator(wrapContent).start()
                        text = "Make me tall!"
                    }
                }
            }

        }.lparams(matchParent, wrapContent)
    }
}