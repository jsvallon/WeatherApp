package com.llcvmlr.weatherappchallenge.network.di.data

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

/**
 * Dagger module that provides dependencies related to location services.
 *
 * This module is responsible for providing instances of [FusedLocationProviderClient] and [Context]
 * that are scoped to the lifecycle of an activity.
 *
 * The module is installed in the [ActivityComponent], which means the provided dependencies
 * are available for the duration of the activity's lifecycle.
 */
@Module
@InstallIn(ActivityComponent::class)
object LocationModule {

    /**
     * Provides an instance of [FusedLocationProviderClient] using the given [Context].
     *
     * The [FusedLocationProviderClient] is used to access the device's location services,
     * providing a simple and high-level API to interact with location services.
     *
     * @param context The activity context used to initialize the [FusedLocationProviderClient].
     * @return An instance of [FusedLocationProviderClient] for accessing location services.
     */
    @Provides
    fun provideFusedLocationProviderClient(@ActivityContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    /**
     * Provides the [Context] scoped to the activity.
     *
     * This method returns the provided context to ensure that any component that requires
     * context can receive the activity context it needs.
     *
     * @param context The activity context.
     * @return The activity context.
     */
    @Provides
    fun provideContext(@ActivityContext context: Context): Context {
        return context
    }
}
