package com.llcvmlr.weatherappchallenge.framework.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val id: Int,
    val name: String,
    val cod: Int
): Parcelable


//data class SearchResult(
//    val searchInfo: WeatherResponse
//)

@Parcelize
data class Coord(val lon: Double, val lat: Double): Parcelable

@Parcelize
data class Weather(val id: Int, val main: String, val description: String, val icon: String): Parcelable

@Parcelize
data class Main(val temp: Double, val pressure: Int, val humidity: Int, val temp_min: Double, val temp_max: Double): Parcelable

@Parcelize
data class Wind(val speed: Double, val deg: Int): Parcelable

@Parcelize
data class Clouds(val all: Int): Parcelable

@Parcelize
data class Sys(val type: Int, val id: Int, val country: String, val sunrise: Long, val sunset: Long): Parcelable



enum class NetWorkResponse { SUCCESS_200, ERROR_403, ERROR_404 }