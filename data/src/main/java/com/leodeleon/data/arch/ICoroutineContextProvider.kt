package com.leodeleon.data.arch

import kotlin.coroutines.CoroutineContext

interface ICoroutineContextProvider {
    fun io(): CoroutineContext
    fun main(): CoroutineContext
    fun bg(): CoroutineContext
}