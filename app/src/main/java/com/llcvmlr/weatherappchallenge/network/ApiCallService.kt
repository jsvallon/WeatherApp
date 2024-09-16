package com.llcvmlr.weatherappchallenge.network


import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for making API calls related to weather information.
 *
 * This interface defines methods for retrieving weather data from a remote API based on
 * either a city name or geographic coordinates. The methods return [Deferred] objects for
 * asynchronous handling of API responses.
 */
interface ApiCallService {

    /**
     * Retrieves weather information for a specific city.
     *
     * @param query The name of the city for which to fetch weather information.
     * @param appid The API key used for authentication with the weather service.
     * @return A [Deferred] object containing the [Response] with the [WeatherResponse] for the requested city.
     */
    @GET(NetWorkConstant.WEATHER_ENDPOINT)
    fun getWeatherByCity(
        @Query("q") query: String,
        @Query("appid") appid: String
    ): Deferred<Response<WeatherResponse>>

    /**
     * Retrieves weather information based on geographic coordinates.
     *
     * @param latitude The latitude of the location for which to fetch weather information.
     * @param longitude The longitude of the location for which to fetch weather information.
     * @param apiKey The API key used for authentication with the weather service.
     * @return A [Deferred] object containing the [Response] with the [WeatherResponse] for the requested coordinates.
     */
    @GET(NetWorkConstant.WEATHER_ENDPOINT)
    fun getWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): Deferred<Response<WeatherResponse>>
}
