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
import uz.rounded.montekrist.domain.model.starship.StarshipModel

interface RecyclerViewItemClickListener {
    fun onItemClick(position: Int)
    fun onItemDoubleTap(starshipModel: StarshipModel)
}

class StarshipAdapter(
    private val itemClickListener: RecyclerViewItemClickListener,
) : ListAdapter<StarshipModel, StarshipAdapter.MyViewHolder>(TaskDiffCallBack()) {

    class TaskDiffCallBack : DiffUtil.ItemCallback<StarshipModel>() {
        override fun areItemsTheSame(
            oldItem: StarshipModel, newItem: StarshipModel
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: StarshipModel, newItem: StarshipModel
        ): Boolean {
            return oldItem._id == newItem._id
        }
    }

    inner class MyViewHolder(private var itemBinding: ItemStarshipBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(data: StarshipModel) {

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