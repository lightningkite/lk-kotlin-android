package lk.anko.adapters



import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

/**
 * A literally empty [RecyclerView.Adapter].
 * Created by joseph on 9/20/16.
 */
class EmptyRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = 0
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        throw UnsupportedOperationException()
    }
}