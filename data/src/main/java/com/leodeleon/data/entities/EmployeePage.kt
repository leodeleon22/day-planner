package com.leodeleon.data.entities

import com.squareup.moshi.Json

data class EmployeePage(
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "total")
    val total: Int,
    @Json(name = "data")
    val data: List<EmployeeItemDto>
)