package com.leodeleon.data.remote

import com.leodeleon.data.entities.Session
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("connect/token")
    suspend fun authenticate(@FieldMap hashMap: Map<String, String>): Response<Session>
}