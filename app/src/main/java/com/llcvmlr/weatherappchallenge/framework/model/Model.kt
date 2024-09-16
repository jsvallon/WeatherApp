package com.llcvmlr.weatherappchallenge.framework.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents the weather data received from an API response.
 *
 * @property coord The geographic coordinates (longitude and latitude) of the location.
 * @property weather A list of weather conditions observed.
 * @property base The base station for the weather data (e.g., stations).
 * @property main Main weather parameters (e.g., temperature, pressure, humidity).
 * @property visibility The visibility in meters.
 * @property wind The wind conditions (e.g., speed, direction).
 * @property clouds The cloudiness percentage.
 * @property dt The timestamp of the data.
 * @property sys Additional system data (e.g., country, sunrise, sunset).
 * @property id The unique identifier of the location.
 * @property name The name of the location.
 * @property cod The HTTP response code.
 */
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

/**
 * Represents the geographic coordinates of the location.
 *
 * @property lon Longitude of the location.
 * @property lat Latitude of the location.
 */
@Parcelize
data class Coord(
    val lon: Double,
    val lat: Double
): Parcelable

/**
 * Represents weather conditions observed.
 *
 * @property id The unique identifier for the weather condition.
 * @property main The main weather condition (e.g., Rain, Snow).
 * @property description A detailed description of the weather condition.
 * @property icon The weather icon code.
 */
@Parcelize
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
): Parcelable

/**
 * Represents main weather parameters.
 *
 * @property temp The temperature in Kelvin.
 * @property pressure The atmospheric pressure in hPa.
 * @property humidity The humidity percentage.
 * @property temp_min The minimum temperature in Kelvin.
 * @property temp_max The maximum temperature in Kelvin.
 */
@Parcelize
data class Main(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val temp_min: Double,
    val temp_max: Double
): Parcelable

/**
 * Represents wind conditions.
 *
 * @property speed The wind speed in meters per second.
 * @property deg The wind direction in degrees.
 */
@Parcelize
data class Wind(
    val speed: Double,
    val deg: Int
): Parcelable

/**
 * Represents cloudiness data.
 *
 * @property all The cloudiness percentage.
 */
@Parcelize
data class Clouds(
    val all: Int
): Parcelable

/**
 * Represents additional system information.
 *
 * @property type The type of the system.
 * @property id The unique identifier for the system.
 * @property country The country code.
 * @property sunrise The sunrise time as a Unix timestamp.
 * @property sunset The sunset time as a Unix timestamp.
 */
@Parcelize
data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
): Parcelable

/**
 * Enumeration representing possible network response states.
 */
enum class NetWorkResponse {
    SUCCESS_200, // HTTP 200 OK
    ERROR_403,   // HTTP 403 Forbidden
    ERROR_404    // HTTP 404 Not Found
}
