package com.llcvmlr.weatherappchallenge.network.di.data


import com.llcvmlr.weatherappchallenge.framework.data.DefaultSearchContentsRepository
import com.llcvmlr.weatherappchallenge.framework.data.SearchContentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsSearchContentsRepository(
        searchContentsRepository: DefaultSearchContentsRepository,
    ): SearchContentsRepository
}