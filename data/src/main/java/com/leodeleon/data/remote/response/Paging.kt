package com.leodeleon.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Paging(
    val offset: Int?,
    val limit: Int,
    val total: Int
)