package com.llcvmlr.weatherappchallenge.framework.uix.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.llcvmlr.weatherappchallenge.framework.uix.AppNameScreen
import com.llcvmlr.weatherappchallenge.framework.uix.screen.SearchRoute
import com.llcvmlr.weatherappchallenge.framework.uix.viewmodel.SearchViewModel

/**
 * Sets up the navigation graph for the Weather App.
 *
 * @param modifier [Modifier] to apply to the NavHost.
 * @param navController [NavHostController] used to control navigation.
 * @param startDestination [String] that specifies the initial screen to display. Defaults to [AppNameScreen.SearchScreen.name].
 * @param searchViewModel [SearchViewModel] instance used by the [SearchRoute] composable.
 */
@Composable
fun WeatherAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = AppNameScreen.SearchScreen.name,
    searchViewModel: SearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = AppNameScreen.SearchScreen.name) {
            SearchRoute(searchViewModel = searchViewModel)
        }
    }
}
