package com.leodeleon.data.ktx

import com.leodeleon.data.arch.Result
import retrofit2.Response

inline fun <reified T> Response<T>.toResult(): Result<T> {
    return if (isSuccessful) {
        body()?.let {
            Result.Success(it)
        } ?: Result.Empty
    } else {
        Result.Error(Exception(message()))
    }
}

inline fun <reified T, R> Response<T>.mapResult(map: (T) -> R): Result<R> {
    return if (isSuccessful) {
        body()?.let {
            Result.Success(map(it))
        } ?: Result.Empty
    } else {
        Result.Error(Exception(message()))
    }
}