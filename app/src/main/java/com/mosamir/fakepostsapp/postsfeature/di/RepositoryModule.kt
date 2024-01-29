package com.mosamir.fakepostsapp.postsfeature.di

import com.mosamir.fakepostsapp.postsfeature.data.data_source.remote.IPostsDataSource
import com.mosamir.fakepostsapp.postsfeature.data.repository.PostsRepo
import com.mosamir.fakepostsapp.postsfeature.domain.repository.IPostsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    fun providePostsRepo(iPostsDataSource: IPostsDataSource):IPostsRepo
            = PostsRepo(iPostsDataSource)


}