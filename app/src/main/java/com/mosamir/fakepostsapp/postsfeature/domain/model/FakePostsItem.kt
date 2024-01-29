package com.mosamir.fakepostsapp.postsfeature.domain.model

data class FakePostsItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)