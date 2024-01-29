package com.mosamir.fakepostsapp.postsfeature.domain.repository

import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.util.IResult

interface IPostsRepo {

    suspend fun getAllPosts(): IResult<List<FakePostsItem>>

    suspend fun getPostById(postId: Int): IResult<FakePostsItem>

}