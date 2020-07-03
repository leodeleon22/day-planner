package com.leodeleon.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeItemDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val departments: List<Int>
)