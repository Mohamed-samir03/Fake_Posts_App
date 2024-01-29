package com.mosamir.fakepostsapp.postsfeature.presentation.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mosamir.fakepostsapp.R
import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem

class PostAdapter(private val onItemClick: (FakePostsItem) -> Unit = {}) : ListAdapter<FakePostsItem, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        // Set up item click listener
        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentItem)
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postTitle: TextView = itemView.findViewById(R.id.postTitle)

        fun bind(item: FakePostsItem) {
            postTitle.text = item.title
        }
    }

    private class PostDiffCallback : DiffUtil.ItemCallback<FakePostsItem>() {
        override fun areItemsTheSame(oldItem: FakePostsItem, newItem: FakePostsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FakePostsItem, newItem: FakePostsItem): Boolean {
            return oldItem == newItem
        }
    }
}