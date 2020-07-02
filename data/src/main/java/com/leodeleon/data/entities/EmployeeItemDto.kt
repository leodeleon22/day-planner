package com.leodeleon.data.entities

import com.squareup.moshi.Json

data class EmployeeItemDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "departments")
    val departments: List<Int>
)