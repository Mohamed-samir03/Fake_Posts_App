package com.mosamir.fakepostsapp.postsfeature.di

import com.mosamir.fakepostsapp.postsfeature.data.data_source.remote.IPostsDataSource
import com.mosamir.fakepostsapp.postsfeature.data.data_source.remote.PostsApiService
import com.mosamir.fakepostsapp.postsfeature.data.data_source.remote.PostsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Provides
    fun providePostsDataSource(apiService: PostsApiService):IPostsDataSource = PostsDataSource(apiService)


}