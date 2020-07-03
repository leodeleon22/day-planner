package com.leodeleon.data.remote

import com.leodeleon.data.arch.ICoroutineContextProvider
import com.leodeleon.data.arch.Result
import com.leodeleon.data.local.PreferenceManager
import com.leodeleon.data.repos.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@ExperimentalSplittiesApi
class TokenAuthenticator @Inject constructor(
    val repository: AuthRepository,
    val dispatchers: ICoroutineContextProvider
) : Authenticator, CoroutineScope {
    private val job = SupervisorJob()

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking(dispatchers.io()) {
            val result = repository.authenticate()
            when (result) {
                is Result.Success -> {
                    val token = result.data.access_token
                    PreferenceManager.setToken(token)

                    response.request
                        .newBuilder()
                        .header("Authorization", "Bearer $token")
                        .build()
                }
                else -> null
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = dispatchers.io() + job
}