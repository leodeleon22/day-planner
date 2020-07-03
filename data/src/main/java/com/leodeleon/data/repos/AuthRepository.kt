package com.leodeleon.data.repos

import com.leodeleon.data.BuildConfig
import com.leodeleon.data.arch.Result
import com.leodeleon.data.entities.Session
import com.leodeleon.data.ktx.toResult
import com.leodeleon.data.remote.api.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthService
) {

    suspend fun authenticate(): Result<Session> {
        return api.authenticate(hashMapOf(
            "client_id" to BuildConfig.PLANDAY_CLIENT_ID,
            "grant_type" to "refresh_token",
            "refresh_token" to BuildConfig.PLANDAY_REFRESH_TOKEN
        )).toResult()
    }
}