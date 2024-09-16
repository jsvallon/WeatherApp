package com.llcvmlr.weatherappchallenge.network.di.data


import com.llcvmlr.weatherappchallenge.framework.data.DefaultSearchContentsRepository
import com.llcvmlr.weatherappchallenge.framework.data.SearchContentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger module that provides dependencies for data-related components.
 *
 * This module is responsible for binding the implementation of [SearchContentsRepository]
 * to its default implementation [DefaultSearchContentsRepository].
 *
 * The module is installed in the [SingletonComponent], which means the provided dependencies
 * will be available for the entire lifecycle of the application.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    /**
     * Binds the [DefaultSearchContentsRepository] implementation to the [SearchContentsRepository] interface.
     *
     * This method provides the dependency injection framework with the binding information,
     * allowing Dagger to provide instances of [SearchContentsRepository] using the [DefaultSearchContentsRepository] implementation.
     *
     * @param searchContentsRepository The [DefaultSearchContentsRepository] instance to be bound.
     * @return The [SearchContentsRepository] instance.
     */
    @Binds
    internal abstract fun bindsSearchContentsRepository(
        searchContentsRepository: DefaultSearchContentsRepository,
    ): SearchContentsRepository
}
