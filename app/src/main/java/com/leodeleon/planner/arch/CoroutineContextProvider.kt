package com.leodeleon.planner.arch

import com.leodeleon.data.arch.ICoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutineContextProvider : ICoroutineContextProvider {
    override fun io(): CoroutineContext = Dispatchers.IO

    override fun main(): CoroutineContext = Dispatchers.Main

    override fun bg(): CoroutineContext = Dispatchers.Default
}
