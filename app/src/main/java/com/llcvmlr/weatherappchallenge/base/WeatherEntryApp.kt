package com.llcvmlr.weatherappchallenge.base

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.llcvmlr.weatherappchallenge.framework.uix.navigation.WeatherAppNavHost
import com.llcvmlr.weatherappchallenge.framework.uix.viewmodel.SearchViewModel


@Composable
fun WeatherEntryApp(
    searchViewModel: SearchViewModel
) {
    val navController = rememberNavController()
    WeatherAppNavHost(navController = navController, searchViewModel = searchViewModel)
}