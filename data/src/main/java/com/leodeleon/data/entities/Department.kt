package com.leodeleon.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Department(
    val id: Int,
    val name: String
)