package com.llcvmlr.weatherappchallenge.framework.data

import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import com.llcvmlr.weatherappchallenge.network.Result

/**
 * Interface defining the contract for the search data operations in the repository layer.
 *
 * This interface specifies methods for querying weather content and getting search content count.
 */
interface SearchContentsRepository {

    /**
     * Queries the contents based on the provided [searchQuery].
     *
     * This method is responsible for retrieving weather data matching the search query.
     * The result is returned as a [Flow] of [Result] containing either a [WeatherResponse] or an error.
     *
     * @param searchQuery The query string used to search for weather data.
     * @return A [Flow] of [Result] where [Result.Success] contains a [WeatherResponse] or [Result.Error] contains an error.
     */
    fun searchContents(searchQuery: String): Flow<Result<WeatherResponse>>

    /**
     * Provides the count of search contents.
     *
     * This method returns a [Flow] of integer value representing the count of search contents.
     *
     * @return A [Flow] of integer value representing the count of search contents.
     */
    fun getSearchContentsCount(): Flow<Int>
}
