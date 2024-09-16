package com.llcvmlr.weatherappchallenge.framework.core

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.llcvmlr.weatherappchallenge.framework.core.ui.PreviewParameterData.mockWeatherResponse
import com.llcvmlr.weatherappchallenge.framework.uix.SearchResultUiState

/**
 * Provides preview data for the [SearchResultUiState] class to be used in Jetpack Compose previews.
 *
 * This [PreviewParameterProvider] implementation supplies a sequence of [SearchResultUiState] objects
 * for use in composable function previews. By providing mock data, it helps visualize how the UI will
 * appear in different states based on the data structure defined in [SearchResultUiState].
 *
 * The provided data is intended to help developers test and validate UI components during design time,
 * ensuring that different states and scenarios are represented in the previews.
 *
 * Example usage:
 * ```
 * @Preview
 * @Composable
 * fun SearchResultPreview(
 *     @PreviewParameter(SearchUiStatePreviewParameterProvider::class) previewState: SearchResultUiState
 * ) {
 *     // Your composable that uses SearchResultUiState
 * }
 * ```
 *
 * @see PreviewParameterProvider
 */
class SearchUiStatePreviewParameterProvider : PreviewParameterProvider<SearchResultUiState> {
    /**
     * A sequence of [SearchResultUiState] objects to be used for preview purposes.
     */
    override val values: Sequence<SearchResultUiState> = sequenceOf(
        SearchResultUiState.Success(
            weatherResponse = mockWeatherResponse
        ),
    )
}
