package com.mosamir.fakepostsapp.postsfeature.data.data_source.remote

import com.mosamir.fakepostsapp.postsfeature.data.mapper.asDomain
import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem
import com.mosamir.fakepostsapp.util.IResult
import com.mosamir.fakepostsapp.util.NetworkState
import javax.inject.Inject

class PostsDataSource @Inject constructor(
    private val postsApiService: PostsApiService
) : IPostsDataSource {

    override suspend fun getAllPosts(): IResult<List<FakePostsItem>> {
        return try {
            val posts = postsApiService.getAllPosts()
            return IResult.onSuccess(posts.map {
                it.asDomain()
            })
        }catch (ex:Exception){
            IResult.onFail(NetworkState.getErrorMessage(ex).msg.toString())
        }
    }

    override suspend fun getPostById(postId: Int): IResult<FakePostsItem> {
        return try {
            val posts = postsApiService.getPostById(postId)
            return IResult.onSuccess(posts.asDomain())
        }catch (ex:Exception){
            IResult.onFail(NetworkState.getErrorMessage(ex).msg.toString())
        }
    }

}