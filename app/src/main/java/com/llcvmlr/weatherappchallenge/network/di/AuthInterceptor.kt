package com.llcvmlr.weatherappchallenge.network.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * An [Interceptor] implementation for adding authentication headers to HTTP requests.
 *
 * This class is used to intercept and modify HTTP requests to include necessary authentication
 * headers before they are sent to the server.
 *
 * @property context The application context, which may be used for accessing resources or
 * other application-level functionalities.
 */
@Singleton
class AuthInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    /**
     * Intercepts an HTTP request and adds authentication headers if needed.
     *
     * This method is called before the request is sent. It allows for modification of the request,
     * such as adding authentication headers, or other custom logic. Currently, this implementation
     * does not add any headers but provides a place to do so if required in the future.
     *
     * @param chain The [Interceptor.Chain] object that allows you to proceed with the request or
     * modify it.
     * @return The [Response] object resulting from the intercepted request.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        return chain.proceed(requestBuilder.build())
    }
}
