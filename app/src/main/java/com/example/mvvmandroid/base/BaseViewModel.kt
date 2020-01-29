package com.example.mvvmandroid.base

import androidx.lifecycle.ViewModel
import com.example.mvvmandroid.injection.component.DaggerViewModelInjector
import com.example.mvvmandroid.injection.component.ViewModelInjector
import com.example.mvvmandroid.injection.module.NetworkModule
import com.example.mvvmandroid.ui.PostListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector =
        DaggerViewModelInjector.builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }

}
