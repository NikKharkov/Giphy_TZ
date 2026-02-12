package com.example.giphy_tz.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.giphy_tz.data.GifObject
import com.example.giphy_tz.databinding.ItemGifBinding

class GifItemAdapter(
    private val onGifClick: (String) -> Unit
) : PagingDataAdapter<GifObject, GifItemAdapter.GifViewHolder>(GifDiffCallback) {

    inner class GifViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GifObject) {
            binding.gifTitle.text = item.title
            binding.gif.load(item.images.preview.url) {
                crossfade(true)
            }
            binding.root.setOnClickListener {
                onGifClick(item.images.original.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = ItemGifBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GifViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: GifViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    object GifDiffCallback : DiffUtil.ItemCallback<GifObject>() {
        override fun areItemsTheSame(oldItem: GifObject, newItem: GifObject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GifObject, newItem: GifObject): Boolean {
            return oldItem == newItem
        }
    }
}