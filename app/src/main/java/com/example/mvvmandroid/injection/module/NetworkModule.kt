package com.example.mvvmandroid.injection.module

import com.example.mvvmandroid.network.PostApi
import com.example.mvvmandroid.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Provides all required dependencies about network.
 * */

@Module
@Suppress("unused")
object NetworkModule {

    /**
     * Provides the Post service.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service
     * */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    /**
     * Provide Retrofit object.
     * @return Retrofit object
     * */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io()))
            .build()
    }
}