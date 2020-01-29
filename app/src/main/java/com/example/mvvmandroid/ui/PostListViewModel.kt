package com.example.mvvmandroid.ui

import android.view.View
import androidx.core.R
import androidx.lifecycle.MutableLiveData
import com.example.mvvmandroid.base.BaseViewModel
import com.example.mvvmandroid.model.Post
import com.example.mvvmandroid.model.PostDao
import com.example.mvvmandroid.network.PostApi
import com.example.mvvmandroid.utils.PostListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao) : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val postListAdapter = PostListAdapter()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = Observable.fromCallable { postDao.all }
            .concatMap { dbPostList ->
                if (dbPostList.isEmpty()) {
                    postApi.getPosts().concatMap { apiPostList ->
                        postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                }else{
                    Observable.just(dbPostList)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListError() {
        errorMessage.value = com.example.mvvmandroid.R.string.post_error
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        postListAdapter.updatePostList(postList)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}