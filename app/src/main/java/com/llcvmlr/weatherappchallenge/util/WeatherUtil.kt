package com.llcvmlr.weatherappchallenge.util

import android.content.Context
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import com.llcvmlr.weatherappchallenge.util.PreferenceHelper.cityName
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A utility class for managing weather-related data and preferences.
 *
 * This class provides methods to interact with weather-related data stored in preferences, including
 * saving and retrieving city names and weather responses.
 */
@Singleton
class WeatherUtil @Inject constructor() {

    /**
     * Retrieves the saved weather response from preferences.
     *
     * @return The [WeatherResponse] object saved in preferences, or `null` if no data is found.
     */
    fun getSavingSchoolDataResponse(): WeatherResponse? {
        return ModelPreferencesManager.get<WeatherResponse>(WeatherConstant.WEATHER_DATA_SAVING)
    }

    /**
     * Saves the provided city name to the SharedPreferences.
     *
     * @param context The context used to access SharedPreferences.
     * @param cityName The name of the city to be saved.
     */
    fun saveCityName(
        context: Context,
        cityName: String,
    ) {
        val prefs = PreferenceHelper.customPreference(context, WeatherConstant.CUSTOM_PREF_NAME)
        prefs.cityName = cityName
    }

    /**
     * Retrieves the city name from SharedPreferences.
     *
     * @param context The context used to access SharedPreferences.
     * @return The city name stored in preferences, or `null` if no city name is found.
     */
    fun getCityName(
        context: Context
    ): String? {
        return PreferenceHelper.customPreference(context, WeatherConstant.CUSTOM_PREF_NAME).cityName
    }
}
