package com.mosamir.fakepostsapp.postsfeature.domain.use_case

import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.util.IResult

interface IGetPostByIdUseCase {
    suspend fun getPostById(postId: Int): IResult<FakePostsItem>
}