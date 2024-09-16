package com.llcvmlr.weatherappchallenge.framework.uix

import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse

/**
 * A sealed interface representing the different states of a search result in the weather application.
 *
 * This interface is used to model the various states that the UI can be in while performing a search.
 */
sealed interface SearchResultUiState {

    /**
     * Represents a loading state where the search operation is in progress.
     */
    data object Loading : SearchResultUiState

    /**
     * Represents a state where the search query is empty or too short.
     *
     * This state is used to differentiate between the initial state (when the search query is cleared)
     * and when no search results are returned.
     */
    data object EmptyQuery : SearchResultUiState

    /**
     * Represents a state where no search results are found.
     */
    data object EmptyResult : SearchResultUiState

    /**
     * Represents a state where the search operation has failed.
     */
    data object LoadFailed : SearchResultUiState

    /**
     * Represents a successful search result.
     *
     * @property weatherResponse The [WeatherResponse] object containing the details of the search result.
     *
     * @constructor Creates an instance of [Success] with the given [weatherResponse].
     *
     * @param weatherResponse The [WeatherResponse] object containing weather details.
     *
     * @return `true` if the weather response contains no weather data, `false` otherwise.
     */
    data class Success(
        val weatherResponse: WeatherResponse
    ) : SearchResultUiState {
        fun isEmpty(): Boolean = weatherResponse.weather.isEmpty()
    }

    /**
     * Represents a state where the search contents are not ready.
     *
     * This state occurs when the Full-Text Search (FTS) tables are not yet populated.
     */
    data object SearchNotReady : SearchResultUiState
}
