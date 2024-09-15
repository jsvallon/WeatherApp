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


@Composable
fun WeatherItem(
    weatherResponse: WeatherResponse,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Ensures content is centered horizontally
    ) {
        // Weather icon at the top
        Image(
            painter = rememberAsyncImagePainter(
                "https://openweathermap.org/img/wn/${weatherResponse.weather.first().icon}@2x.png"
            ),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp) // Adjust size if needed
                .padding(bottom = 8.dp) // Space between image and text
        )

        // Weather details below the icon
        Text(weatherResponse.name)
        Text("${weatherResponse.main.temp}°C")
        Text(weatherResponse.weather.first().description)
    }
}



@DevicePreviews
@Composable
private fun InterestsCardWithEmptyDescriptionPreview(
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
