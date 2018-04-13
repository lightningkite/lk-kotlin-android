package lk.android.animations



import android.animation.ValueAnimator

/**
 * Properly typed value animators.
 * Created by jivie on 6/30/16.
 */
object TypedValueAnimator {

    /**
     * A value animator that animates between [Int] values.
     */
    class IntAnimator(vararg values: Int) : ValueAnimator() {
        init {
            setIntValues(*values)
        }

        fun onUpdate(lambda: IntAnimator.(Int) -> Unit): IntAnimator {
            addUpdateListener {
                lambda(animatedValue as Int)
            }
            return this
        }
    }

    /**
     * A value animator that animates between [Float] values.
     */
    class FloatAnimator(vararg values: Float) : ValueAnimator() {
        init {
            setFloatValues(*values)
        }

        fun onUpdate(lambda: FloatAnimator.(Float) -> Unit): FloatAnimator {
            addUpdateListener {
                lambda(animatedValue as Float)
            }
            return this
        }
    }
}