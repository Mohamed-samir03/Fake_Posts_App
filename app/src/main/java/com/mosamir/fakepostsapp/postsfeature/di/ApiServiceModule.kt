package com.mosamir.fakepostsapp.postsfeature.di

import com.mosamir.fakepostsapp.postsfeature.data.data_source.remote.PostsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {


    @Provides
    fun providePostsApiService(retrofit: Retrofit):PostsApiService
            = retrofit.create(PostsApiService::class.java)


}