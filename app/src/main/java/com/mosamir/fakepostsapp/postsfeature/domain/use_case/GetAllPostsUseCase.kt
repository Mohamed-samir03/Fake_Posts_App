package com.mosamir.fakepostsapp.postsfeature.domain.use_case

import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.postsfeature.domain.repository.IPostsRepo
import com.mosamir.fakepostsapp.util.IResult
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(
private val iPostsRepo: IPostsRepo
) : IGetAllPostsUseCase {
    override suspend fun getAllPosts(): IResult<List<FakePostsItem>> {
        return iPostsRepo.getAllPosts()
    }
}