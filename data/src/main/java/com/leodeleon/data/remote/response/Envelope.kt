package com.leodeleon.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Envelope<T>(val data: T)