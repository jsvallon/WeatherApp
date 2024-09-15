package com.llcvmlr.weatherappchallenge.network


import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCallService {

    @GET(NetWorkConstant.WEATHER)
    fun getWeatherByCity(
        @Query("q") query: String,
        @Query("appid") appid: String
    ): Deferred<Response<WeatherResponse>>


    @GET(NetWorkConstant.WEATHER)
    fun getWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): Deferred<Response<WeatherResponse>>
}