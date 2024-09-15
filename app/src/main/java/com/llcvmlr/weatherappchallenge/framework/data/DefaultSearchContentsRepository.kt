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


class DefaultSearchContentsRepository @Inject constructor(
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val apiCallService: ApiCallService
)  : SearchContentsRepository {

    override fun searchContents(searchQuery: String): Flow<Result<WeatherResponse>> {
        return flow {
            try {
                val response: Response<WeatherResponse> = apiCallService.getWeatherByCity(query = searchQuery, appid = NetWorkConstant.APP_ID).await()
                println("SearchViewModel response $response")

                if (response.isSuccessful) {
                    val result: WeatherResponse? = response.body()
                    if (result != null) {
                        emit(Result.Success(result))
                    } else {
                        println("SearchViewModel else print empty response")
                        emit(Result.Error(Exception("SearchViewModel: Empty result")))
                    }
                } else {
                    emit(Result.Error(Exception("SearchViewModel: Unsuccessful response: ${response.code()}")))
                }
            } catch (e: Exception) {
                emit(Result.Error(e))
                println("SearchViewModel message: ${e.message}")
            }
        }
    }

    override fun getSearchContentsCount(): Flow<Int> {
        return flow { emit(WeatherConstant.EMIT_VAL) }
    }
}
