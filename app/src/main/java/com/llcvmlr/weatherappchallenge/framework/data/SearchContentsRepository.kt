package com.llcvmlr.weatherappchallenge.framework.data

import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import com.llcvmlr.weatherappchallenge.network.Result
/**
 * Data layer interface for the search feature
 * */
interface SearchContentsRepository {

    /**
     * Query the contents matched with the [searchQuery] and returns it as a [Flow] of [WeatherResponse]
     * */
    fun searchContents(searchQuery: String): Flow<Result<WeatherResponse>>

    fun getSearchContentsCount(): Flow<Int>
}