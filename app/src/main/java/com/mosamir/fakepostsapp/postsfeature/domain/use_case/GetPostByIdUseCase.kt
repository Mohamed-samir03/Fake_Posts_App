package com.mosamir.fakepostsapp.postsfeature.domain.use_case

import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.postsfeature.domain.repository.IPostsRepo
import com.mosamir.fakepostsapp.util.IResult
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val iPostsRepo: IPostsRepo
) : IGetPostByIdUseCase{
    override suspend fun getPostById(postId: Int): IResult<FakePostsItem> {
        return iPostsRepo.getPostById(postId)
    }
}