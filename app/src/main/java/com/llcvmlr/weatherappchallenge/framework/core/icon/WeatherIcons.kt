package com.llcvmlr.weatherappchallenge.framework.core.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A singleton object that provides Material Design icons for the Weather App.
 *
 * This object serves as a centralized location for accessing commonly used
 * icons throughout the application. It uses Material Design's [ImageVector]
 * icons for consistency and easy integration with Jetpack Compose UI components.
 *
 * The icons are defined as follows:
 *
 * - [Close]: Represents a "close" icon from Material Design's rounded icon set.
 * - [Search]: Represents a "search" icon from Material Design's rounded icon set.
 *
 * These icons are used for common UI actions and should be used throughout the
 * app to maintain a consistent look and feel.
 *
 * Example usage:
 * ```
 * Icon(
 *     imageVector = WeatherIcons.Close,
 *     contentDescription = "Close"
 * )
 * ```
 *
 * @see Icons
 * @see ImageVector
 * @see androidx.compose.material.icons.rounded
 */
object WeatherIcons {
    // Material Design icon for closing or dismissing
    val Close: ImageVector = Icons.Rounded.Close

    // Material Design icon for search functionality
    val Search: ImageVector = Icons.Rounded.Search
}
