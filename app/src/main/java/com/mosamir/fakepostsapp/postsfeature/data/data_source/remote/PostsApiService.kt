package com.mosamir.fakepostsapp.postsfeature.data.data_source.remote

import com.mosamir.fakepostsapp.postsfeature.data.model.FakePostsResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

const val POSTS = "posts"
const val POST_BY_ID = "posts/{postId}"

interface PostsApiService {

    @GET(POSTS)
    suspend fun getAllPosts(): List<FakePostsResponseItem>

    @GET(POST_BY_ID)
    suspend fun getPostById(@Path("postId") postId: Int): FakePostsResponseItem

}