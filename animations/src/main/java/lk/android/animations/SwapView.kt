package lk.android.animations



import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout

/**
 * A view that swaps between views that are passed in.
 *
 * Created by joseph on 1/19/18.
 */
class SwapView(context: Context) : FrameLayout(context) {
    val manager = ViewSwapManager(this, { FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT) })
    fun swap(newView: View, animation: AnimationSet = AnimationSet.fade) = manager.swap(newView, animation)
}