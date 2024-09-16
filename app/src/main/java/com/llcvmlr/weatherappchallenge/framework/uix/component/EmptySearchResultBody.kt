package com.llcvmlr.weatherappchallenge.framework.uix.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.llcvmlr.weatherappchalleneg.R


/**
 * A composable function that displays a message when no search results are found.
 *
 * This UI component is displayed when the user's search yields no results. It provides a message indicating
 * that no results were found for the search query and offers a clickable suggestion to perform another search.
 *
 * @param onItemSearchEmptyClick A lambda function to be invoked when the user clicks on the "Try another search" text.
 * @param searchQuery The search query that yielded no results. This query will be highlighted in the message.
 *
 * @sample
 * ```
 * EmptySearchResultBody(
 *     onItemSearchEmptyClick = { /* Handle click */ },
 *     searchQuery = "example query"
 * )
 * ```
 *
 * The `EmptySearchResultBody` composable function consists of the following elements:
 *
 * - A centered `Text` element that displays a message indicating that no results were found. The search query
 *   is highlighted in bold within the message.
 * - A `ClickableText` element that provides a suggestion to "Try another search." This text is styled with
 *   underline and bold attributes, and when clicked, it triggers the `onItemSearchEmptyClick` lambda function.
 *
 * The overall layout is a `Column` with horizontal alignment centered and padding applied for spacing.
 *
 * **Usage Example:**
 * ```
 * @Composable
 * fun MyScreen() {
 *     EmptySearchResultBody(
 *         onItemSearchEmptyClick = { /* Handle try another search click */ },
 *         searchQuery = "test"
 *     )
 * }
 * ```
 */

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

