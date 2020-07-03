package com.leodeleon.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeItemDto(
    @Json(name = "id")
    val id: Int,
    val firstName: String,
    val lastName: String,
    val departments: List<Int>
)