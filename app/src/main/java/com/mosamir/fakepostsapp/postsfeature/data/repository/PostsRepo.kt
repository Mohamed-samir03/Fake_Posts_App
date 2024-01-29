package com.mosamir.fakepostsapp.postsfeature.data.repository

import com.mosamir.fakepostsapp.postsfeature.data.data_source.remote.IPostsDataSource
import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.postsfeature.domain.repository.IPostsRepo
import com.mosamir.fakepostsapp.util.IResult
import javax.inject.Inject

class PostsRepo @Inject constructor(
    private val iPostsDataSource: IPostsDataSource
)  : IPostsRepo {

    override suspend fun getAllPosts(): IResult<List<FakePostsItem>> {
        return iPostsDataSource.getAllPosts()
    }

    override suspend fun getPostById(postId: Int): IResult<FakePostsItem> {
        return iPostsDataSource.getPostById(postId)
    }

}