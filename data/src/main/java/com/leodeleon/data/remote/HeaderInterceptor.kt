package com.leodeleon.data.remote

import com.leodeleon.data.BuildConfig
import com.leodeleon.data.local.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import splitties.experimental.ExperimentalSplittiesApi

class HeaderInterceptor : Interceptor {
    @ExperimentalSplittiesApi
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newRequest = original.newBuilder()
            .addHeader("X-ClientId", BuildConfig.PLANDAY_CLIENT_ID)
            .addHeader("Authorization", "Bearer ${PreferenceManager.getToken()}")
            .build()
        return chain.proceed(newRequest)
    }
}