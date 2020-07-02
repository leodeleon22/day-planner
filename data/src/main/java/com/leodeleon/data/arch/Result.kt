package com.leodeleon.data.arch

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable?) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object Empty : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
            Empty -> "Empty"
        }
    }
}

val Result<*>.isSuccess
    get() = this is Result.Success && data != null

val Result<*>.isError
    get() = this is Result.Error && exception != null

val Result<*>.isEmptyList
    get() = this is Result.Success && data is Collection<*> && !data.isNullOrEmpty()