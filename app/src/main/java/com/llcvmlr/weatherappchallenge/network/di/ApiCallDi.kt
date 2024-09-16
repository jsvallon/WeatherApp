package com.llcvmlr.weatherappchallenge.network.di


import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.llcvmlr.weatherappchallenge.network.ApiCallService
import com.llcvmlr.weatherappchallenge.network.NetWorkConstant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

/**
 * Dagger module that provides dependencies for network operations and API calls.
 *
 * This module is responsible for providing instances of network-related components such as
 * [Retrofit], [OkHttpClient], and [ApiCallService]. It is installed in the [SingletonComponent],
 * meaning that the provided dependencies have a singleton lifespan within the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiCallDi {

    /**
     * Provides the base URL for network requests.
     *
     * This URL is used as the root URL for all API requests made by [Retrofit].
     *
     * @return The base URL as a [String].
     */
    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return NetWorkConstant.BASE_URL
    }

    /**
     * Provides an instance of [Moshi] for JSON serialization and deserialization.
     *
     * [Moshi] is used to convert JSON responses into Kotlin data classes and vice versa.
     *
     * @return An instance of [Moshi] configured with a [KotlinJsonAdapterFactory].
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Provides an instance of [AuthInterceptor] for adding authentication headers to requests.
     *
     * The [AuthInterceptor] is used to add necessary headers to network requests for authentication purposes.
     *
     * @param context The application context used to initialize the [AuthInterceptor].
     * @return An instance of [AuthInterceptor].
     */
    @Provides
    @Singleton
    fun provideAuthInterceptor(
        @ApplicationContext context: Context
    ): AuthInterceptor {
        return AuthInterceptor(context)
    }

    /**
     * Provides an instance of [OkHttpClient] for handling HTTP requests and responses.
     *
     * The [OkHttpClient] is configured with an [AuthInterceptor] to manage authentication headers.
     *
     * @param context The application context used to initialize the [AuthInterceptor].
     * @return An instance of [OkHttpClient] configured with the [AuthInterceptor].
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

    /**
     * Provides an instance of [Retrofit] for making network requests.
     *
     * [Retrofit] is used to convert API responses into Kotlin data classes and to manage network calls.
     *
     * @param baseUrl The base URL for network requests.
     * @param moshi The [Moshi] instance used for JSON serialization and deserialization.
     * @param okHttpClient The [OkHttpClient] instance used for handling HTTP requests.
     * @return An instance of [Retrofit] configured with [ScalarsConverterFactory], [MoshiConverterFactory], and [CoroutineCallAdapterFactory].
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String? = NetWorkConstant.BASE_URL,
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl ?: NetWorkConstant.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    /**
     * Provides an instance of [ApiCallService] for making API calls.
     *
     * [ApiCallService] is an interface defining the endpoints and methods for interacting with the API.
     *
     * @param retrofit The [Retrofit] instance used to create the [ApiCallService] instance.
     * @return An instance of [ApiCallService].
     */
    @Provides
    @Singleton
    fun provideApiCallService(
        retrofit: Retrofit
    ): ApiCallService {
        return retrofit.create(ApiCallService::class.java)
    }
}
