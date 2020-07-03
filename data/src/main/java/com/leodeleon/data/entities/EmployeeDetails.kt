package com.leodeleon.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeDetails(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val gender: Gender?
)