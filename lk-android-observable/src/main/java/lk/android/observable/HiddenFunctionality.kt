@file:JvmName("LkAndroidObservable")
@file:JvmMultifileClass

package lk.android.observable



import android.graphics.PointF
import android.view.MotionEvent
import android.view.View
import lk.kotlin.observable.property.ObservableProperty
import lk.kotlin.observable.property.StandardObservableProperty

/**
 * Allows you to add in "secret codes" to views.
 * When the user taps the view correctly, the [action] will be invoked.
 * @param requirements The requirements for code, in order.  Each requirement is a predicate that is
 * given a point with both x and y between 0 and 1, representing how far the point is. 0 would be
 * left/top, and 1 would be right/bottom.
 */
fun View.hiddenTouchFunctionality(requirements: List<PointF.() -> Boolean>, action: () -> Unit): ObservableProperty<Int> {
    val passwordStageObs = StandardObservableProperty(0)
    val point = PointF()
    setOnTouchListener { view, motionEvent ->
        if (motionEvent.action != MotionEvent.ACTION_DOWN) return@setOnTouchListener false
        point.x = (motionEvent.x) / view.width
        point.y = (motionEvent.y) / view.height
        val requirement = requirements[passwordStageObs.value]
        if (requirement(point))
            passwordStageObs.value++
        else
            passwordStageObs.value = 0
        if (passwordStageObs.value == requirements.size) {
            action()
            passwordStageObs.value = 0
        }
        true
    }
    return passwordStageObs
}


/**
 * Allows you to add in "secret codes" to views.
 * When the user taps the view correctly, the [action] will be invoked.
 *
 * Example: using booleanArrayOf(false, true, true, false, true) would require the user to tap
 * (left, right, right, left, right) on the view to activate the action.
 *
 * @param rightSide Whether or not the tap at this stage should be on the right side or left side of
 * the view.
 */
fun View.hiddenTouchFunctionality(rightSide: BooleanArray, action: () -> Unit): ObservableProperty<Int> = hiddenTouchFunctionality(
        rightSide.map {
            if (it) {
                { point: PointF -> point.x > .5f }
            } else {
                { point: PointF -> point.x < .5f }
            }
        },
        action
)