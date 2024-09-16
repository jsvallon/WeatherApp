package com.llcvmlr.weatherappchallenge.framework.data

import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import com.llcvmlr.weatherappchallenge.network.ApiCallService
import com.llcvmlr.weatherappchallenge.network.NetWorkConstant
import com.llcvmlr.weatherappchallenge.network.di.Dispatcher
import com.llcvmlr.weatherappchallenge.network.di.WeatherDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import com.llcvmlr.weatherappchallenge.network.Result
import com.llcvmlr.weatherappchallenge.util.WeatherConstant

/**
 * Implementation of the [SearchContentsRepository] interface that handles data operations
 * for searching weather content based on a city name.
 *
 * This repository interacts with the [ApiCallService] to fetch weather data and processes
 * the responses to emit results wrapped in a [Result] object. It uses a specified [CoroutineDispatcher]
 * to handle background operations efficiently.
 *
 * @param ioDispatcher The [CoroutineDispatcher] used for IO operations.
 * @param apiCallService The service used to make network requests.
 */
class DefaultSearchContentsRepository @Inject constructor(
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val apiCallService: ApiCallService
) : SearchContentsRepository {

    /**
     * Searches for weather content based on the provided query.
     *
     * Makes a network request to fetch weather data for the given city name and returns
     * the result as a [Flow] of [Result] containing either the [WeatherResponse] or an error.
     *
     * @param searchQuery The city name to search for weather data.
     * @return A [Flow] of [Result] containing the [WeatherResponse] or an error.
     */
    override fun searchContents(searchQuery: String): Flow<Result<WeatherResponse>> {
        return flow {
            try {
                // Make the network request to fetch weather data
                val response: Response<WeatherResponse> = apiCallService.getWeatherByCity(
                    query = searchQuery,
                    appid = NetWorkConstant.APP_ID
                ).await()

                if (response.isSuccessful) {
                    val result: WeatherResponse? = response.body()
                    if (result != null) {
                        // Emit the result if successful and data is not null
                        emit(Result.Success(result))
                    } else {
                        // Emit an error if the result is null
                        emit(Result.Error(Exception("SearchViewModel: Empty result")))
                    }
                } else {
                    // Emit an error if the response is not successful
                    emit(Result.Error(Exception("SearchViewModel: Unsuccessful response: ${response.code()}")))
                }
            } catch (e: Exception) {
                // Emit an error if an exception is thrown
                emit(Result.Error(e))
            }
        }
    }

    /**
     * Provides the count of search contents.
     *
     * This method emits a fixed value defined by [WeatherConstant.EMIT_VAL].
     *
     * @return A [Flow] containing the count of search contents.
     */
    override fun getSearchContentsCount(): Flow<Int> {
        return flow { emit(WeatherConstant.EMIT_VAL) }
    }
}
