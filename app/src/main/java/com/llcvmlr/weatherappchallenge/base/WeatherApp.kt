package com.llcvmlr.weatherappchallenge.base

import android.app.Application
import com.llcvmlr.weatherappchallenge.util.ModelPreferencesManager
import dagger.hilt.android.HiltAndroidApp

/**
 * The [WeatherApp] class is the application class for the Weather App.
 * It extends the [Application] class and is annotated with [@HiltAndroidApp].
 * This annotation triggers Hilt's code generation, which includes generating a base
 * class that uses Hilt for dependency injection throughout the application.
 *
 * [WeatherApp] is responsible for initializing the application-wide dependencies and settings
 * when the application starts.
 *
 * Initialization:
 * - [ModelPreferencesManager] is initialized in the `onCreate()` method using the application context.
 *
 * Dependencies:
 * - [ModelPreferencesManager]: Manages application-specific preferences.
 *
 * @see Application
 * @see HiltAndroidApp
 * @see ModelPreferencesManager
 */
@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize the ModelPreferencesManager with the application context
        ModelPreferencesManager.with(this)
    }
}
