package com.llcvmlr.weatherappchallenge.framework.core.ui

import com.llcvmlr.weatherappchallenge.framework.model.Clouds
import com.llcvmlr.weatherappchallenge.framework.model.Coord
import com.llcvmlr.weatherappchallenge.framework.model.Main
import com.llcvmlr.weatherappchallenge.framework.model.Sys
import com.llcvmlr.weatherappchallenge.framework.model.Weather
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import com.llcvmlr.weatherappchallenge.framework.model.Wind

/**
 * A singleton object that provides mock data for previewing UI components
 * in the Weather App.
 *
 * This object contains sample data used for testing and previewing UI components
 * in Jetpack Compose. The mock data is designed to resemble a typical response
 * from the weather API to facilitate UI development without requiring actual API calls.
 *
 * The mock data is structured as follows:
 *
 * - [mockWeatherResponse]: An instance of [WeatherResponse] containing
 *   sample data for weather-related information.
 *
 * Example usage:
 * ```
 * @Preview
 * @Composable
 * fun WeatherScreenPreview() {
 *     WeatherScreen(weatherResponse = PreviewParameterData.mockWeatherResponse)
 * }
 * ```
 *
 * The mock data includes:
 * - [Coord]: Coordinates with longitude and latitude.
 * - [Weather]: A list of weather conditions with an ID, main description, detailed description, and icon code.
 * - [Main]: Main weather data including temperature (in Kelvin), pressure, humidity, and temperature range.
 * - [Wind]: Wind data with speed and direction.
 * - [Clouds]: Cloud coverage percentage.
 * - [Sys]: System-related data including country code, sunrise and sunset times.
 * - Other fields such as visibility, timestamp, location ID, and location name.
 *
 * @see WeatherResponse
 * @see Coord
 * @see Weather
 * @see Main
 * @see Wind
 * @see Clouds
 * @see Sys
 */
object PreviewParameterData {

    // Sample mock weather response for previewing UI components
    val mockWeatherResponse = WeatherResponse(
        coord = Coord(
            lon = -0.13,
            lat = 51.51
        ),
        weather = listOf(
            Weather(
                id = 300,
                main = "Drizzle",
                description = "light intensity drizzle",
                icon = "09d"
            )
        ),
        base = "stations",
        main = Main(
            temp = 280.32,  // Temperature in Kelvin
            pressure = 1012,
            humidity = 81,
            temp_min = 279.15,
            temp_max = 281.15
        ),
        visibility = 10000,
        wind = Wind(
            speed = 4.1,
            deg = 80
        ),
        clouds = Clouds(
            all = 90
        ),
        dt = 1485789600,
        sys = Sys(
            type = 1,
            id = 5091,
            country = "GB",
            sunrise = 1485762037,
            sunset = 1485794875
        ),
        id = 2643743,
        name = "London",
        cod = 200
    )
}
