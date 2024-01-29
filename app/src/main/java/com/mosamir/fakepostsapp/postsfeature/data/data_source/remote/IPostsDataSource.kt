package com.mosamir.fakepostsapp.postsfeature.data.data_source.remote

import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.util.IResult

interface IPostsDataSource {

    suspend fun getAllPosts(): IResult<List<FakePostsItem>>

    suspend fun getPostById(postId: Int): IResult<FakePostsItem>

}