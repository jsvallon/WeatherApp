package com.llcvmlr.weatherappchallenge.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * A Dagger [Module] that provides coroutine scopes for the application.
 *
 * This module is responsible for providing a [CoroutineScope] that is used throughout the
 * application. It ensures that coroutines launched within this scope have a SupervisorJob,
 * allowing for better error handling and cancellation.
 */
@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineScopesModule {

    /**
     * Provides a [CoroutineScope] for the application with a [SupervisorJob] and the specified
     * [CoroutineDispatcher].
     *
     * This [CoroutineScope] is intended to be used for application-wide coroutines that need to
     * run with a specific dispatcher, such as the default dispatcher.
     *
     * @param dispatcher The [CoroutineDispatcher] to be used with this [CoroutineScope]. It
     *                   determines the thread or thread pool on which coroutines will run.
     * @return A [CoroutineScope] configured with a [SupervisorJob] and the provided [dispatcher].
     */
    @Provides
    @Singleton
    @ApplicationScope
    fun providesCoroutineScope(
        @Dispatcher(WeatherDispatchers.Default) dispatcher: CoroutineDispatcher
    ) : CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}

/**
 * Qualifier annotation to specify the application-wide [CoroutineScope].
 *
 * This annotation is used to distinguish between different coroutine scopes within the application.
 * It is used in conjunction with Dagger's dependency injection to provide the appropriate scope.
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
