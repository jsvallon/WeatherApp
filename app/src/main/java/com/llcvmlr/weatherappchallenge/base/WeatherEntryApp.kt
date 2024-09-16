package com.llcvmlr.weatherappchallenge.base

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.llcvmlr.weatherappchallenge.framework.uix.navigation.WeatherAppNavHost
import com.llcvmlr.weatherappchallenge.framework.uix.viewmodel.SearchViewModel

/**
 * The [WeatherEntryApp] composable function is the entry point for the Weather App's
 * user interface. It sets up the navigation and provides the necessary [SearchViewModel]
 * for managing UI state and interactions.
 *
 * This composable function initializes the navigation controller and passes it along
 * with the [SearchViewModel] to the [WeatherAppNavHost], which is responsible for handling
 * the navigation logic and displaying different screens within the app.
 *
 * @param searchViewModel An instance of [SearchViewModel] used to manage the state
 *                        and business logic for the search functionality in the app.
 *
 * @Composable
 * @see rememberNavController
 * @see WeatherAppNavHost
 * @see SearchViewModel
 */
@Composable
fun WeatherEntryApp(
    searchViewModel: SearchViewModel
) {
    // Create a navigation controller to manage navigation between different screens
    val navController = rememberNavController()

    // Set up the navigation host with the navigation controller and search view model
    WeatherAppNavHost(
        navController = navController,
        searchViewModel = searchViewModel
    )
}

/**
 * App bar to display title and conditionally display the back navigation.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.primary
) {
    Extracted()
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor
        )
    )
}

@Composable
private fun Extracted() {
    val systemUiController = rememberSystemUiController()
    val backgroundColorStatus = MaterialTheme.colorScheme.surfaceVariant
    SideEffect {
        systemUiController.setStatusBarColor(
            color = backgroundColorStatus,
            darkIcons = true
        )
    }
}
