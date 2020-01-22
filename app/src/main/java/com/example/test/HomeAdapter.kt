package com.example.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.data.GankItem
import com.example.test.databinding.ItemHomeBinding
import com.example.test.util.EmailSwipeActionDrawable

class HomeAdapter : ListAdapter<GankItem, HomeHolder>(HomeDiff()) {
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
        holder.bind(getItem(position))
    }


}

class HomeDiff : DiffUtil.ItemCallback<GankItem>() {
    override fun areItemsTheSame(oldItem: GankItem, newItem: GankItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GankItem, newItem: GankItem): Boolean {
        return oldItem.url == newItem.url
    }

}

class HomeHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.background = EmailSwipeActionDrawable(itemView.context)
    }

    fun bind(item: GankItem) {
        binding.news = item
    }
}