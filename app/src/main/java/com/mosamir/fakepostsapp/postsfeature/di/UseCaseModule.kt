package com.mosamir.fakepostsapp.postsfeature.di

import com.mosamir.fakepostsapp.postsfeature.domain.repository.IPostsRepo
import com.mosamir.fakepostsapp.postsfeature.domain.use_case.GetAllPostsUseCase
import com.mosamir.fakepostsapp.postsfeature.domain.use_case.GetPostByIdUseCase
import com.mosamir.fakepostsapp.postsfeature.domain.use_case.IGetAllPostsUseCase
import com.mosamir.fakepostsapp.postsfeature.domain.use_case.IGetPostByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Provides
    fun provideGetAllPostsUseCase(iPostsRepo: IPostsRepo):IGetAllPostsUseCase = GetAllPostsUseCase(iPostsRepo)

    @Provides
    fun provideGetPostByIdUseCase(iPostsRepo: IPostsRepo):IGetPostByIdUseCase = GetPostByIdUseCase(iPostsRepo)

}