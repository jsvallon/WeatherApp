package com.llcvmlr.weatherappchallenge.framework.domain

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.llcvmlr.weatherappchallenge.framework.core.DevicePreviews
import com.llcvmlr.weatherappchallenge.framework.core.SearchUiStatePreviewParameterProvider
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import com.llcvmlr.weatherappchallenge.framework.uix.SearchResultUiState
import com.llcvmlr.weatherappchallenge.ui.theme.WeatherAppChallenegTheme

/**
 * A Composable function that displays a weather item with an icon, temperature, and description.
 *
 * @param weatherResponse The [WeatherResponse] object containing weather details to display.
 * @param modifier Optional [Modifier] for customizing the layout and appearance of the weather item.
 */
@Composable
fun WeatherItem(
    weatherResponse: WeatherResponse,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the weather icon at the top
        Image(
            painter = rememberAsyncImagePainter(
                "https://openweathermap.org/img/wn/${weatherResponse.weather.first().icon}@2x.png"
            ),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp) // Set the size of the icon
                .padding(bottom = 8.dp) // Space below the icon
        )

        // Display the weather details below the icon
        Text(text = weatherResponse.name) // Location name
        Text(text = "${weatherResponse.main.temp}°C") // Temperature in Celsius
        Text(text = weatherResponse.weather.first().description) // Weather description
    }
}

/**
 * Preview for the [WeatherItem] composable, using sample data from [SearchUiStatePreviewParameterProvider].
 */
@DevicePreviews
@Composable
private fun WeatherItemPreview(
    @PreviewParameter(SearchUiStatePreviewParameterProvider::class)
    searchResultUiState: SearchResultUiState.Success
) {
    WeatherAppChallenegTheme {
        Surface {
            WeatherItem(
                weatherResponse = searchResultUiState.weatherResponse
            )
        }
    }
}
