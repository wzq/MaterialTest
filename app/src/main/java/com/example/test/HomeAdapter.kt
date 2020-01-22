package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ItemHomeBinding
import com.example.test.util.EmailSwipeActionDrawable
import com.example.test.util.ReboundingSwipeActionCallback
import kotlin.math.abs

class HomeAdapter : ListAdapter<String, HomeHolder>(HomeDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.binding.id = position
        holder.bind(getItem(position))
    }


}

class HomeDiff : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}

class HomeHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root), ReboundingSwipeActionCallback.ReboundableViewHolder {

    init {
        binding.root.background = EmailSwipeActionDrawable(itemView.context)
    }

    fun bind(item: String) {
        binding.root.isActivated = true
    }

    override val reboundableView: View
        get() = binding.cardView

    override fun onReboundOffsetChanged(
        currentSwipePercentage: Float,
        swipeThreshold: Float,
        currentTargetHasMetThresholdOnce: Boolean
    ) {
        if (currentTargetHasMetThresholdOnce) return

        val isStarred = binding.root.isActivated

        // Animate the top left corner radius of the email card as swipe happens.
        val interpolation = (currentSwipePercentage / swipeThreshold).coerceIn(0F, 1F)
        val adjustedInterpolation = abs((if (isStarred) 1F else 0F) - interpolation)
        binding.cardView.progress = adjustedInterpolation

        // Start the background animation once the threshold is met.
        val thresholdMet = currentSwipePercentage >= swipeThreshold
        val shouldStar = when {
            thresholdMet && isStarred -> false
            thresholdMet && !isStarred -> true
            else -> return
        }
        binding.root.isActivated = shouldStar
    }

    override fun onRebounded() {
    }
}