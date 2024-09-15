package com.llcvmlr.weatherappchallenge.framework.uix.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.llcvmlr.weatherappchallenge.framework.uix.AppNameScreen
import com.llcvmlr.weatherappchallenge.framework.uix.screen.SearchRoute
import com.llcvmlr.weatherappchallenge.framework.uix.viewmodel.SearchViewModel

@Composable
fun WeatherAppNavHost(
    modifier : Modifier = Modifier,
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