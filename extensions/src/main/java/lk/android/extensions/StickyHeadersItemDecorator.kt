package lk.android.extensions



import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Adds sticky headers to a [RecyclerView].
 * @param list A reference to the list that the sticky headers are applied to.
 * @param sort A lambda that sorts the items into categories.
 * @param makeView A lambda that generates a view based on the category.
 * Created by josep on 2/11/2016.
 */
class StickyHeadersItemDecorator<T : Any, K>(
        val list: List<T>,
        val sort: (T) -> K,
        val makeView: (K) -> View
) : RecyclerView.ItemDecoration() {

    val viewHeaders = HashMap<K, View>()

    var firstPos = 0
    fun updateFirstPos(parent: RecyclerView) {
        firstPos = (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }

    fun viewHasHeaderOver(position: Int): Boolean {
        if (position <= 0) return true
        //if(position == firstPos) return true
        if (sort(list[position]) != sort(list[position - 1])) return true
        return false
    }

    fun getHeader(parent: RecyclerView, position: Int): View {
        val sortVal = sort(list[position])
        return viewHeaders[sortVal] ?: {
            val newView = makeView(sortVal)
            newView.measure(
                    View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.UNSPECIFIED
            )
            newView.layout(0, 0, newView.measuredWidth, newView.measuredHeight)
            viewHeaders[sortVal] = newView
            newView
        }()
    }


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        if (list.isEmpty()) return

        updateFirstPos(parent)
        val left = parent.paddingLeft;
        val right = parent.width - parent.paddingRight;

        renderHeaderOnCanvas(c, getHeader(parent, firstPos), 0)

        val childCount = parent.childCount;
        for (i in 0..childCount - 1) {
            val view = parent.getChildAt(i);
            val holder = parent.getChildViewHolder(view)
            val position = holder.adapterPosition
            if (position < 0 || position >= list.size) continue

            if (viewHasHeaderOver(position)) {
                val header = getHeader(parent, position)
                val top = view.run {
                    top - (
                            (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin ?: 0
                            ) + (translationY + .5f).toInt()
                }.minus(header.height)
                if (top < 0) continue

                renderHeaderOnCanvas(c, header, top, view.alpha)
            }
        }
    }

    private fun renderHeaderOnCanvas(c: Canvas, header: View, top: Int, alpha: Float = 1f) {
        c.save()

        c.translate(0f, top.toFloat())
        c.clipRect(0, 0, header.width, header.height)

        header.alpha = alpha
        header.draw(c)

        c.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        updateFirstPos(parent)
        val holder = parent.getChildViewHolder(view)
        val position = holder.adapterPosition
        if (position < 0 || position >= list.size) return
        if (viewHasHeaderOver(position)) outRect.top = getHeader(parent, position).measuredHeight
        else outRect.top = 0
    }
}