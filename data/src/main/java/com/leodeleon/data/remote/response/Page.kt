package com.leodeleon.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Page <T> (
    val paging: Paging,
    val data: T
)