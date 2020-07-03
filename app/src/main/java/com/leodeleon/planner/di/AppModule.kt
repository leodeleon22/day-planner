package com.leodeleon.planner.di

import com.leodeleon.data.arch.ICoroutineContextProvider
import com.leodeleon.data.arch.ISchedulerProvider
import com.leodeleon.data.remote.HeaderInterceptor
import com.leodeleon.data.remote.TokenAuthenticator
import com.leodeleon.data.remote.api.AuthService
import com.leodeleon.data.remote.api.HRService
import com.leodeleon.data.repos.AuthRepository
import com.leodeleon.data.repos.EmployeeRepository
import com.leodeleon.planner.arch.CoroutineContextProvider
import com.leodeleon.planner.arch.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Qualifier
import javax.inject.Singleton

@ExperimentalSplittiesApi
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private const val BASE_URL = "https://openapi.planday.com/"
    private const val AUTH_URL = "https://id.planday.com/"

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AuthClient

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NoAuthClient

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun providesHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Singleton
    @Provides
    fun providesTokenAuthenticator(
        repository: AuthRepository,
        dispatchers: ICoroutineContextProvider
    ): Authenticator {
        return TokenAuthenticator(repository, dispatchers)
    }

    @Singleton
    @NoAuthClient
    @Provides
    fun provideNoAuthClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(logger)
            .build()
    }

    @ExperimentalSplittiesApi
    @Singleton
    @AuthClient
    @Provides
    fun provideClient(
        logger: HttpLoggingInterceptor,
        header: HeaderInterceptor,
        authenticator: Authenticator
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(header)
            .addInterceptor(logger)
            .authenticator(authenticator)
            .build()
    }

    @Singleton
    @Provides
    fun providesHRService(@AuthClient client: OkHttpClient): HRService = run {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HRService::class.java)
    }

    @Singleton
    @Provides
    fun providesAuthService(@NoAuthClient client: OkHttpClient): AuthService = run {
        Retrofit.Builder()
            .baseUrl(AUTH_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun providesAuthRepository(api: AuthService): AuthRepository =
        AuthRepository(api)

    @Singleton
    @Provides
    fun providesEmployeeRepository(api: HRService, schedulers: ISchedulerProvider): EmployeeRepository =
        EmployeeRepository(api, schedulers)

    @Singleton
    @Provides
    fun provideSchedulers(): ISchedulerProvider {
        return SchedulerProvider()
    }

    @Singleton
    @Provides
    fun provideDispatchers(): ICoroutineContextProvider {
        return CoroutineContextProvider()
    }
}
