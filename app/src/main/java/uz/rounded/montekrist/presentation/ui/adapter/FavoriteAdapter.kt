package uz.rounded.montekrist.presentation.ui.adapter

import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.rounded.montekrist.databinding.ItemStarshipBinding
import uz.rounded.montekrist.domain.model.starship.Starship
import uz.rounded.montekrist.domain.model.starship.StarshipModel

interface FavoriteItemClickListener {
    fun onItemClick(position: Int)
    fun onItemDoubleTap(starshipModel: Starship)
}

class FavoriteAdapter(
    private val itemClickListener: FavoriteItemClickListener,
) : ListAdapter<Starship, FavoriteAdapter.MyViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<Starship>() {
        override fun areItemsTheSame(
            oldItem: Starship, newItem: Starship
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Starship, newItem: Starship
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private var itemBinding: ItemStarshipBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(data: Starship) {

            itemBinding.apply {
                name.text = data.name
                type.text = data.starship_class
                price.text = data.cost_in_credits
            }

            val gestureDetector = GestureDetectorCompat(itemView.context,
                object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapUp(e: MotionEvent): Boolean {
                        itemClickListener.onItemClick(adapterPosition)
                        return true
                    }

                    override fun onDoubleTap(e: MotionEvent): Boolean {
                        itemClickListener.onItemDoubleTap(data)
                        return true
                    }
                })


            itemView.setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
                true
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemStarshipBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.onBind(data)
    }
}