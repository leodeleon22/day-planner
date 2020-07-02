package com.leodeleon.data.entities

import com.squareup.moshi.Json

data class Session(
    @Json(name = "access_token")
    val access_token: String)