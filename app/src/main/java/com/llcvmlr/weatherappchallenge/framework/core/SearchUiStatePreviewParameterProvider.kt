package com.llcvmlr.weatherappchallenge.framework.core

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.llcvmlr.weatherappchallenge.framework.core.ui.PreviewParameterData.mockWeatherResponse
import com.llcvmlr.weatherappchallenge.framework.uix.SearchResultUiState


/**
 * This [PreviewParameterProvider](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/PreviewParameterProvider)
 * provides list of [SearchResultUiState] for Composable previews.
 */
class SearchUiStatePreviewParameterProvider : PreviewParameterProvider<SearchResultUiState> {
    override val values: Sequence<SearchResultUiState> = sequenceOf(
        SearchResultUiState.Success(
            weatherResponse = mockWeatherResponse
        ),
    )
}