package com.llcvmlr.weatherappchallenge.network.di



import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * A Dagger [Module] that provides coroutine dispatchers for the application.
 *
 * This module is responsible for providing instances of [CoroutineDispatcher] for different
 * dispatcher types used in the application. It allows for dependency injection of these
 * dispatchers, making it easier to manage and test coroutine-based operations.
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    /**
     * Provides the [CoroutineDispatcher] for I/O operations.
     *
     * This dispatcher is optimized for I/O-bound tasks and is backed by a shared thread pool
     * designed for efficient handling of I/O operations.
     *
     * @return A [CoroutineDispatcher] for I/O operations, specifically [Dispatchers.IO].
     */
    @Provides
    @Dispatcher(WeatherDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * Provides the [CoroutineDispatcher] for default operations.
     *
     * This dispatcher is optimized for CPU-bound tasks and is backed by a shared thread pool
     * designed for general-purpose use.
     *
     * @return A [CoroutineDispatcher] for default operations, specifically [Dispatchers.Default].
     */
    @Provides
    @Dispatcher(WeatherDispatchers.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
