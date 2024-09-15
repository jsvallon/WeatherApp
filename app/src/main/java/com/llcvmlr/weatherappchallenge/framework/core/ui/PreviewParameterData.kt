package com.llcvmlr.weatherappchallenge.framework.core.ui

import com.llcvmlr.weatherappchallenge.framework.model.Clouds
import com.llcvmlr.weatherappchallenge.framework.model.Coord
import com.llcvmlr.weatherappchallenge.framework.model.Main
import com.llcvmlr.weatherappchallenge.framework.model.Sys
import com.llcvmlr.weatherappchallenge.framework.model.Weather
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import com.llcvmlr.weatherappchallenge.framework.model.Wind


object PreviewParameterData {

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
            temp = 280.32,  // In Kelvin
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