package com.leodeleon.data.entities

import com.squareup.moshi.Json

data class Department(
    @Json(name = "id")
    val id: Int,
    @Json(name = "id")
    val name: String
)