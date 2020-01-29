package com.example.mvvmandroid.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmandroid.R
import com.example.mvvmandroid.databinding.ActivityPostListBinding
import com.example.mvvmandroid.ui.PostListViewModel
import com.google.android.material.snackbar.Snackbar

class PostListActivityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostListBinding

    private lateinit var postListViewModel: PostListViewModel

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_post_list
        )
        binding.postList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        postListViewModel = ViewModelProviders.of(this)
            .get(PostListViewModel::class.java)
        binding.postListViewModel = postListViewModel
        postListViewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) {
                showError(errorMessage)
            }
        })
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(
            binding.root,
            errorMessage, Snackbar.LENGTH_INDEFINITE
        )
        errorSnackbar?.setAction(R.string.retry,
            postListViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

}
