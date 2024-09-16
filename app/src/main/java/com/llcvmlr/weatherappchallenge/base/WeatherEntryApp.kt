package com.llcvmlr.weatherappchallenge.base

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
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
