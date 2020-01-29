package com.example.mvvmandroid.injection.component

import com.example.mvvmandroid.injection.module.NetworkModule
import com.example.mvvmandroid.ui.PostListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    fun inject(postListViewModel: PostListViewModel)

    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}