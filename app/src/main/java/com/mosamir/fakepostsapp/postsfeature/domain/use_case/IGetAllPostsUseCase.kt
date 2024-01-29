package com.mosamir.fakepostsapp.postsfeature.domain.use_case

import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.util.IResult

interface IGetAllPostsUseCase {

    suspend fun getAllPosts(): IResult<List<FakePostsItem>>

}