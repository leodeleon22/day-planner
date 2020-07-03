package com.leodeleon.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Session(
    val access_token: String)