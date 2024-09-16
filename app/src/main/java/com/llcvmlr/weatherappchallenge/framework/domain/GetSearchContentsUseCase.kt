package com.llcvmlr.weatherappchallenge.framework.domain

import com.llcvmlr.weatherappchallenge.network.Result
import com.llcvmlr.weatherappchallenge.framework.data.SearchContentsRepository
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving search contents based on a search query.
 *
 * This use case interacts with the [SearchContentsRepository] to fetch weather data
 * that matches the provided search query.
 */
class GetSearchContentsUseCase @Inject constructor(
    private val searchContentsRepository: SearchContentsRepository
) {

    /**
     * Executes the use case to retrieve search contents based on the provided query.
     *
     * This operator function delegates the call to the [SearchContentsRepository]'s
     * `searchContents` method, returning the results as a [Flow] of [Result] wrapped around
     * [WeatherResponse].
     *
     * @param searchQuery The query string used to search for weather data.
     * @return A [Flow] of [Result] containing either a successful [WeatherResponse] or an error.
     */
    operator fun invoke(
        searchQuery: String
    ): Flow<Result<WeatherResponse>> =
        searchContentsRepository.searchContents(searchQuery)
}
