@file:JvmName("LkAnkoAdapters")
@file:JvmMultifileClass

package lk.anko.adapters



import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper

/**
 * Listener that allows you to easily make two swipe actions on a [RecyclerView].
 * Created by jivie on 2/11/16.
 */
open class SwipeActionListener(
        val leftAction: SwipeAction? = null,
        val rightAction: SwipeAction? = null,
        val drawablePadding: Int = 0
) : ItemTouchHelper.Callback() {

    data class SwipeAction(val color: Int, val drawable: Drawable, val canDo: (Int) -> Boolean, val action: (Int) -> Unit)

    override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {
        var swipeDirections = 0
        if (leftAction != null) swipeDirections = swipeDirections or ItemTouchHelper.LEFT
        if (rightAction != null) swipeDirections = swipeDirections or ItemTouchHelper.RIGHT
        return makeMovementFlags(0, swipeDirections)
    }

    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean = false

    @Suppress("UNCHECKED_CAST")
    override fun onSwiped(holder: RecyclerView.ViewHolder, swipeDirection: Int) {
        if (swipeDirection == ItemTouchHelper.LEFT) {
            leftAction?.action?.invoke(holder.adapterPosition)
        }
        if (swipeDirection == ItemTouchHelper.RIGHT) {
            rightAction?.action?.invoke(holder.adapterPosition)
        }
    }

    val drawRect: RectF = RectF()

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            val itemView = viewHolder.itemView;

            if (dX > 0) {
                val rightAction = rightAction
                if (rightAction != null) {
                    val icon = rightAction.drawable
                    // swiping right
                    drawRect.set(itemView.left.toFloat(), itemView.top.toFloat(), itemView.left + dX, itemView.bottom.toFloat())

                    val ratio = 1 - dX / itemView.width

                    c.save()
                    c.translate(drawRect.left, drawRect.top)
                    c.clipRect(0f, 0f, drawRect.width(), drawRect.height())

                    c.drawColor((rightAction.color and 0x00FFFFFF) or ((ratio.coerceIn(0f, 1f) * 0xFF).toInt() shl 24))

                    val iconLeft = (drawRect.width() / 2 - icon.minimumWidth / 2).toInt()
                    val iconTop = (drawRect.height() / 2 - icon.minimumHeight / 2).toInt()
                    icon.setBounds(iconLeft, iconTop, iconLeft + icon.minimumWidth, iconTop + icon.minimumHeight)

                    icon.alpha = (ratio * 255).toInt()
                    icon.draw(c)

                    c.restore()
                }

            } else if (dX < 0) {
                val leftAction = leftAction
                if (leftAction != null) {
                    val icon = leftAction.drawable
                    // swiping right
                    drawRect.set(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())

                    val ratio = 1 + dX / itemView.width

                    c.save()
                    c.translate(drawRect.left, drawRect.top)
                    c.clipRect(0f, 0f, drawRect.width(), drawRect.height())

                    c.drawColor((leftAction.color and 0x00FFFFFF) or ((ratio.coerceIn(0f, 1f) * 0xFF).toInt() shl 24))

                    val iconLeft = (drawRect.width() / 2 - icon.minimumWidth / 2).toInt()
                    val iconTop = (drawRect.height() / 2 - icon.minimumHeight / 2).toInt()
                    icon.setBounds(iconLeft, iconTop, iconLeft + icon.minimumWidth, iconTop + icon.minimumHeight)

                    icon.alpha = (ratio * 255).toInt()
                    icon.draw(c)

                    c.restore()
                }

            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);


        }
    }
}

/**
 * Easily make two swipe actions on a [RecyclerView].
 */
fun RecyclerView.swipe(leftAction: SwipeActionListener.SwipeAction?, rightAction: SwipeActionListener.SwipeAction?, padding: Int) {
    val listener = SwipeActionListener(leftAction, rightAction, padding)
    ItemTouchHelper(listener).attachToRecyclerView(this)
}