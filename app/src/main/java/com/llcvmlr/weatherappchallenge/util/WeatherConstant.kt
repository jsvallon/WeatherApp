package com.llcvmlr.weatherappchallenge.util

/**
 * A utility object that holds constant values used throughout the weather application.
 *
 * This object provides various constants related to SharedPreferences, location permissions, and other
 * application-specific values. These constants are used to maintain consistency and avoid hardcoding
 * values in multiple places within the application.
 */
object WeatherConstant {

    // SharedPreferences
    /**
     * The name of the SharedPreferences file used to store user data.
     */
    const val CUSTOM_PREF_NAME: String = "user_data"

    /**
     * The key used to store weather data in SharedPreferences.
     */
    const val WEATHER_DATA_SAVING: String = "weather_data_saving"

    /**
     * A constant representing a single space character, used for formatting purposes.
     */
    const val SPACE: String = " "

    /**
     * A constant value used for emission events in the application.
     */
    const val EMIT_VAL: Int = 1

    // Location
    /**
     * A message displayed when the weather permission is denied.
     */
    const val WEATHER_PERMISSION_DENIED: String = "Permission Denied"
}
