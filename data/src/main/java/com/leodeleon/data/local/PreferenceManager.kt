package com.leodeleon.data.local

import splitties.experimental.ExperimentalSplittiesApi

@ExperimentalSplittiesApi
object PreferenceManager {

    fun setToken(token: String) {
        if (token.isNotEmpty()) {
            AppPreferences.token = token
        }
    }

    fun getToken(): String {
        return AppPreferences.token
    }
}