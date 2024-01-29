package com.mosamir.fakepostsapp.postsfeature.data.model

data class FakePostsResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)