package com.llcvmlr.weatherappchallenge.framework.uix.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.llcvmlr.weatherappchalleneg.R
import com.llcvmlr.weatherappchallenge.framework.core.icon.WeatherIcons
import com.llcvmlr.weatherappchallenge.util.WeatherConstant

/**
 * A composable function that renders a search text field with associated functionality for search queries.
 *
 * This component provides an input field for the user to enter search queries. It includes:
 * - A placeholder text indicating the purpose of the field.
 * - A leading icon for visual cue (search icon).
 * - A trailing icon for clearing the input text when it is not empty.
 * - Handles input changes and keyboard events to trigger searches.
 *
 * The text field also handles focus management and keyboard actions to facilitate search functionality.
 *
 * @param onSearchQueryChanged A lambda function that is invoked when the text in the search field changes.
 *                              It receives the updated query string as a parameter.
 * @param onSearchTriggered A lambda function that is invoked when the search action is triggered.
 *                           It receives the current search query string as a parameter.
 * @param searchQuery The current search query string that will be displayed in the text field.
 *
 * @sample
 * ```
 * @Composable
 * fun SearchScreen(
 *     searchQuery: String,
 *     onSearchQueryChanged: (String) -> Unit,
 *     onSearchTriggered: (String) -> Unit
 * ) {
 *     SearchTextField(
 *         searchQuery = searchQuery,
 *         onSearchQueryChanged = onSearchQueryChanged,
 *         onSearchTriggered = onSearchTriggered
 *     )
 * }
 * ```
 *
 * The `SearchTextField` composable:
 *
 * - Displays a text field with a search icon and a clear button.
 * - Updates the search query as the user types.
 * - Triggers a search action when the user presses "Enter" on the keyboard or taps the search icon.
 * - Requests focus when initially composed.
 *
 * **Usage Example:**
 * ```
 * @Composable
 * fun SearchScreen() {
 *     var searchQuery by remember { mutableStateOf("") }
 *
 *     SearchTextField(
 *         searchQuery = searchQuery,
 *         onSearchQueryChanged = { query -> searchQuery = query },
 *         onSearchTriggered = { query -> performSearch(query) }
 *     )
 * }
 * ```
 */
@Composable
fun SearchTextField(
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
