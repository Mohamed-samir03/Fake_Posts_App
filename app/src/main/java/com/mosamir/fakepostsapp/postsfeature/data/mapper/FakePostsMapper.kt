package com.mosamir.fakepostsapp.postsfeature.data.mapper

import com.mosamir.fakepostsapp.postsfeature.data.model.FakePostsResponseItem
import com.mosamir.fakepostsapp.postsfeature.domain.model.FakePostsItem

fun FakePostsResponseItem.asDomain() = FakePostsItem(
    body = body,
    id = id,
    title = title,
    userId = userId
)