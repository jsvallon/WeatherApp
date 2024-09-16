package com.llcvmlr.weatherappchallenge.network.di

import javax.inject.Qualifier


/**
 * A Dagger [Qualifier] annotation used to distinguish between different [CoroutineDispatcher] instances.
 *
 * This annotation is used to specify which [CoroutineDispatcher] should be injected when
 * multiple dispatchers are available. It helps in providing the correct dispatcher instance
 * based on the context in which it is used.
 *
 * @param weatherDispatchers The [WeatherDispatchers] value that identifies the specific dispatcher.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val weatherDispatchers: WeatherDispatchers)

/**
 * Enum class representing different types of [CoroutineDispatcher] used in the application.
 *
 * This enum defines various dispatcher types that are used for different kinds of tasks,
 * such as I/O operations or general-purpose tasks.
 */
enum class WeatherDispatchers {
    /**
     * The default dispatcher used for CPU-bound tasks and general-purpose operations.
     */
    Default,

    /**
     * The I/O dispatcher used for I/O-bound tasks such as network and file operations.
     */
    IO,
}
