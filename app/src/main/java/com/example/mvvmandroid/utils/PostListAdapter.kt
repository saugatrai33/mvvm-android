package com.example.mvvmandroid.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmandroid.R
import com.example.mvvmandroid.databinding.ItemPostBinding
import com.example.mvvmandroid.model.Post
import com.example.mvvmandroid.ui.PostListViewModel
import com.example.mvvmandroid.ui.post.PostViewModel

class PostListAdapter :
    RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {

    private lateinit var postList: List<Post>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostListViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_post, parent, false
        )
        return PostListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) postList.size else 0
    }

    override fun onBindViewHolder(
        holder: PostListViewHolder,
        position: Int
    ) {
        holder.bind(post = postList[position])
    }

    fun updatePostList(postList: List<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    class PostListViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val postViewModel = PostViewModel()
        fun bind(post: Post) {
            postViewModel.bind(post)
            binding.postViewModel = postViewModel
        }

    }

}