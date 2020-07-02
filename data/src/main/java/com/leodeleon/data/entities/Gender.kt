package com.leodeleon.data.entities

import com.squareup.moshi.Json

enum class Gender {
    @Json(name = "Male")
    Male,

    @Json(name = "Female")
    Female
}