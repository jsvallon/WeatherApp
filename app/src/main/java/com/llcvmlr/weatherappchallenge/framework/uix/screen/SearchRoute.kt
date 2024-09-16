package com.llcvmlr.weatherappchallenge.framework.uix.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.llcvmlr.weatherappchallenge.framework.uix.viewmodel.SearchViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.llcvmlr.weatherappchalleneg.R
import com.llcvmlr.weatherappchallenge.framework.core.DevicePreviews
import com.llcvmlr.weatherappchallenge.framework.core.SearchUiStatePreviewParameterProvider
import com.llcvmlr.weatherappchallenge.framework.domain.WeatherItem
import com.llcvmlr.weatherappchallenge.framework.core.icon.WeatherIcons
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import com.llcvmlr.weatherappchallenge.framework.uix.SearchResultUiState
import com.llcvmlr.weatherappchallenge.framework.uix.common.LoadingDialog
import com.llcvmlr.weatherappchallenge.ui.theme.WeatherAppChallenegTheme
import com.llcvmlr.weatherappchallenge.util.WeatherConstant

/**
 * Composable function that sets up the search screen for the Weather App.
 * Handles the loading state and displays the [SearchScreen] composable.
 *
 * @param modifier [Modifier] to apply to the `SearchRoute`.
 * @param searchViewModel [SearchViewModel] used to manage search queries and results.
 */
@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel
) {

    val searchResultUiState by searchViewModel.searchResults.collectAsStateWithLifecycle()
    val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()
    val isLoading by searchViewModel.isLoading.observeAsState(false)

     // Loading dialog setup
    LoadingDialog(
        showDialog = isLoading,
        onDismissRequest = { searchViewModel.stopLoading() },
        title = stringResource(id = R.string.rlmv_weather_global_text_loading_title),
        message = stringResource(id = R.string.rlmv_weather_global_text_loading_message)
    )

     SearchScreen(
         modifier = modifier,
         onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
         onSearchTriggered = searchViewModel::onSearchTriggered,
         searchQuery = searchQuery,
         searchResultUiState = searchResultUiState
     )
}

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
            SearchToolbar(
                onSearchQueryChanged = onSearchQueryChanged,
                searchQuery = searchQuery,
                onSearchTriggered = onSearchTriggered
            )

            println("SearchViewModel searchResultUiState : $searchResultUiState")

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
private fun SearchToolbar(
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


@Composable
private fun SearchTextField(
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    searchQuery: String
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchQuery)
    }

    TextField(
        placeholder = {
            Text(text = stringResource(id = R.string.rlmv_weather_search_hint))
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            cursorColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = WeatherIcons.Search,
                contentDescription = stringResource(id = R.string.feature_search_title),
                tint = MaterialTheme.colorScheme.surfaceTint
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged(WeatherConstant.SPACE)
                    }
                ) {
                    Icon(
                        imageVector = WeatherIcons.Close,
                        contentDescription = stringResource(
                            id = R.string.rlmv_weather_clear_search_text_content_desc,
                        ),
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }
            }
        },
        onValueChange = {
            if ("\n" !in it) onSearchQueryChanged(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearchExplicitlyTriggered()
                    true
                } else {
                    false
                }
            }
            .testTag("searchTextField"),
        shape = RoundedCornerShape(32.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            }
        ),
        maxLines = 1,
        singleLine = true,
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun EmptySearchResultBody(
    onItemSearchEmptyClick: () -> Unit,
    searchQuery: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 48.dp),
    ) {
        val message = stringResource(id = R.string.rlmv_weather_result_not_found, searchQuery)
        val start = message.indexOf(searchQuery)
        Text(
            text = AnnotatedString(
                text = message,
                spanStyles = listOf(
                    AnnotatedString.Range(
                        SpanStyle(fontWeight = FontWeight.Bold),
                        start = start,
                        end = start + searchQuery.length,
                    ),
                ),
            ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 24.dp),
        )
        val interests = stringResource(id = R.string.rlmv_weather_search)
        val tryAnotherSearchString = buildAnnotatedString {
            append(stringResource(id = R.string.rlmv_weather_try_another_search))
            append(" ")
            withStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                ),
            ) {
                pushStringAnnotation(tag = interests, annotation = interests)
                append(interests)
            }
            append(" ")
            append(stringResource(id = R.string.rlmv_weather_search_to_browse))
        }
        ClickableText(
            text = tryAnotherSearchString,
            style = MaterialTheme.typography.bodyLarge.merge(
                TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                ),
            ),
            modifier = Modifier
                .padding(start = 36.dp, end = 36.dp, bottom = 24.dp)
                .clickable {},
        ) { offset ->
            tryAnotherSearchString.getStringAnnotations(start = offset, end = offset)
                .firstOrNull()
                ?.let { onItemSearchEmptyClick() }
        }
    }
}


@Composable
fun SearchNotReadyBody() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 48.dp),
    ) {
        Text(
            text = stringResource(id = R.string.rlmv_weather_search_not_ready),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 24.dp),
        )
    }
}
@Composable
fun SearchResultBody(
    weatherResponse: WeatherResponse,
) {
    WeatherItem(
        weatherResponse
    )
}


@DevicePreviews
@Composable
private fun SearchToolbarPreview() {
    WeatherAppChallenegTheme {
        SearchToolbar(
            onSearchQueryChanged = {},
            onSearchTriggered = {},
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


