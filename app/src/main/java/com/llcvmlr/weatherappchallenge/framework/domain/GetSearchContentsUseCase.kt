package com.llcvmlr.weatherappchallenge.framework.domain


import com.llcvmlr.weatherappchallenge.network.Result
import com.llcvmlr.weatherappchallenge.framework.data.SearchContentsRepository
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case which returns the searched contents matched with the search query.
 */
class GetSearchContentsUseCase @Inject constructor(
    private val searchContentsRepository: SearchContentsRepository,
) {
    operator fun invoke(
        searchQuery: String,
    ): Flow<Result<WeatherResponse>> =
        searchContentsRepository.searchContents(searchQuery)
}
