package com.example.mvvmandroid.network

import com.example.mvvmandroid.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {
    /**
     * Get list of post from API
     * */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}