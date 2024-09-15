package com.llcvmlr.weatherappchallenge.util

import android.content.Context
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import com.llcvmlr.weatherappchallenge.util.PreferenceHelper.cityName
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherUtil @Inject constructor() {

    fun getSavingSchoolDataResponse(): WeatherResponse? {
        return ModelPreferencesManager.get<WeatherResponse>(WeatherConstant.WEATHER_DATA_SAVING)
    }

    fun saveCityName(
        context: Context,
        cityName: String,
    ) {
        val prefs = PreferenceHelper.customPreference(context, WeatherConstant.CUSTOM_PREF_NAME)
        prefs.cityName = cityName
    }

    fun getCityName(
        context: Context
    ): String? {
        return PreferenceHelper.customPreference(context, WeatherConstant.CUSTOM_PREF_NAME).cityName
    }
}