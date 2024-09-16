package com.llcvmlr.weatherappchallenge.framework.uix.screen


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.llcvmlr.weatherappchalleneg.R
import com.llcvmlr.weatherappchallenge.base.WeatherTopAppBar
import com.llcvmlr.weatherappchallenge.framework.core.DevicePreviews
import com.llcvmlr.weatherappchallenge.framework.core.SearchUiStatePreviewParameterProvider
import com.llcvmlr.weatherappchallenge.framework.uix.SearchResultUiState
import com.llcvmlr.weatherappchallenge.framework.uix.component.LoadingDialog
import com.llcvmlr.weatherappchallenge.framework.uix.viewmodel.SearchViewModel
import com.llcvmlr.weatherappchallenge.ui.theme.WeatherAppChallenegTheme

/**
 * Composable function that sets up the search screen for the Weather App.
 * Handles the loading state and displays the [SearchScreen] composable.
 *
 * @param modifier [Modifier] to apply to the `SearchRoute`.
 * @param searchViewModel [SearchViewModel] used to manage search queries and results.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    val searchResultUiState by searchViewModel.searchResults.collectAsStateWithLifecycle()
    val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()
    val isLoading by searchViewModel.isLoading.observeAsState(false)

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            WeatherTopAppBar(
                title = stringResource(id = R.string.rlmv_weather_home_page),
                scrollBehavior = scrollBehavior,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    ) { innerPadding ->
        // Loading dialog setup
        LoadingDialog(
            showDialog = isLoading,
            onDismissRequest = { searchViewModel.stopLoading() },
            title = stringResource(id = R.string.rlmv_weather_global_text_loading_title),
            message = stringResource(id = R.string.rlmv_weather_global_text_loading_message)
        )

        SearchScreen(
            modifier = modifier
                .padding(innerPadding),
            onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
            onSearchTriggered = searchViewModel::onSearchTriggered,
            searchQuery = searchQuery,
            searchResultUiState = searchResultUiState
        )
    }
}


@DevicePreviews
@Composable
private fun SearchScreenPreview(
    @PreviewParameter(SearchUiStatePreviewParameterProvider::class)
    searchResultUiState: SearchResultUiState,
) {
    WeatherAppChallenegTheme {
        SearchScreen(
            searchResultUiState = searchResultUiState
        )
    }
}


