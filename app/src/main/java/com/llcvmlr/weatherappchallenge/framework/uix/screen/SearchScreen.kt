package com.llcvmlr.weatherappchallenge.framework.uix.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.llcvmlr.weatherappchallenge.framework.core.DevicePreviews
import com.llcvmlr.weatherappchallenge.framework.uix.SearchResultUiState
import com.llcvmlr.weatherappchallenge.framework.uix.component.EmptySearchResultBody
import com.llcvmlr.weatherappchallenge.framework.uix.component.SearchNotReadyBody
import com.llcvmlr.weatherappchallenge.framework.uix.component.SearchResultBody
import com.llcvmlr.weatherappchallenge.framework.uix.component.SearchTextField
import com.llcvmlr.weatherappchallenge.ui.theme.WeatherAppChallenegTheme

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchTriggered: (String) -> Unit = {},
    onItemSearchEmptyClick: () -> Unit = {},
    searchQuery: String = "",
    searchResultUiState: SearchResultUiState
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier.
            background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
            Search(
                onSearchQueryChanged = onSearchQueryChanged,
                searchQuery = searchQuery,
                onSearchTriggered = onSearchTriggered
            )

            when (searchResultUiState) {
                is SearchResultUiState.Loading -> {

                    Unit
                }
                is SearchResultUiState.LoadFailed -> {
                    EmptySearchResultBody(
                        onItemSearchEmptyClick = onItemSearchEmptyClick,
                        searchQuery = searchQuery,
                    )
                }
                is SearchResultUiState.SearchNotReady -> {
                    SearchNotReadyBody()
                }
                is SearchResultUiState.EmptyQuery   -> {
                    Unit
                }
                is SearchResultUiState.Success -> {
                    if (searchResultUiState.isEmpty()) {
                        EmptySearchResultBody(
                            onItemSearchEmptyClick = onItemSearchEmptyClick,
                            searchQuery = searchQuery,
                        )
                    } else {
                        SearchResultBody(
                            weatherResponse = searchResultUiState.weatherResponse
                        )
                    }
                }

                is SearchResultUiState.EmptyResult -> {
                    EmptySearchResultBody(
                        onItemSearchEmptyClick = onItemSearchEmptyClick,
                        searchQuery = searchQuery,
                    )
                }
            }
        }
    }
}


@Composable
private fun Search(
    modifier: Modifier = Modifier,
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String = "",
    onSearchTriggered: (String) -> Unit
) {
    Row (
        verticalAlignment =  Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ){
        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery
        )
    }
}


@DevicePreviews
@Composable
private fun SearchToolbarPreview() {
    WeatherAppChallenegTheme {
        Search(
            onSearchQueryChanged = {},
            onSearchTriggered = {},
        )
    }
}

