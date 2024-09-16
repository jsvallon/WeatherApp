package com.llcvmlr.weatherappchallenge.framework.uix.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.llcvmlr.weatherappchalleneg.R

/**
 * A composable function that displays a message indicating that the search feature is not yet available.
 *
 * This UI component is used to inform the user that the search functionality is currently unavailable.
 * It provides a message centered on the screen to notify the user about the status of the search feature.
 *
 * @sample
 * ```
 * SearchNotReadyBody()
 * ```
 *
 * The `SearchNotReadyBody` composable function consists of:
 *
 * - A `Column` that aligns its content horizontally to the center and provides padding on the horizontal edges.
 * - A centered `Text` element that displays a message indicating that the search feature is not ready.
 *   The text is styled using the `bodyLarge` typography style from the MaterialTheme, and the text alignment is centered.
 *
 * **Usage Example:**
 * ```
 * @Composable
 * fun MyScreen() {
 *     SearchNotReadyBody()
 * }
 * ```
 */
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
