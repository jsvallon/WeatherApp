package com.llcvmlr.weatherappchallenge.framework.uix.component

import androidx.compose.runtime.Composable
import com.llcvmlr.weatherappchallenge.framework.domain.WeatherItem
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse

/**
 * A composable function that displays search results based on the provided [WeatherResponse].
 *
 * This UI component is responsible for rendering the search results using the provided [WeatherResponse] data.
 * It utilizes the [WeatherItem] composable to present the weather information in a specific layout.
 *
 * @param weatherResponse The data object containing weather information to be displayed. This object
 *                        should conform to the [WeatherResponse] model.
 *
 * @sample
 * ```
 * @Composable
 * fun MySearchResults(weatherResponse: WeatherResponse) {
 *     SearchResultBody(weatherResponse = weatherResponse)
 * }
 * ```
 *
 * The `SearchResultBody` composable function:
 *
 * - Takes a [WeatherResponse] object as input.
 * - Passes the [WeatherResponse] object to the [WeatherItem] composable, which handles the actual rendering
 *   of the weather information.
 *
 * **Usage Example:**
 * ```
 * @Composable
 * fun SearchScreen(weatherResponse: WeatherResponse) {
 *     SearchResultBody(weatherResponse = weatherResponse)
 * }
 * ```
 */
@Composable
fun SearchResultBody(
    weatherResponse: WeatherResponse,
) {
    WeatherItem(
        weatherResponse
    )
}
