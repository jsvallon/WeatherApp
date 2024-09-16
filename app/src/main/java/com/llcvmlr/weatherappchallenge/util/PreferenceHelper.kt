package com.llcvmlr.weatherappchallenge.util

import android.content.Context
import android.content.SharedPreferences

/**
 * A utility object for handling SharedPreferences operations in a convenient way.
 *
 * This object provides methods to work with SharedPreferences, including custom preference
 * creation, editing, and storing primitive data types. It simplifies the usage of SharedPreferences
 * by offering extension functions for common operations.
 */
object PreferenceHelper {

    private const val CITY_NAME: String = "cityName"

    /**
     * Creates or retrieves a SharedPreferences instance with the specified name.
     *
     * @param context The context used to access the SharedPreferences.
     * @param name The name of the SharedPreferences file.
     * @return The SharedPreferences instance associated with the given name.
     */
    fun customPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    /**
     * Extension function for SharedPreferences that simplifies editing operations.
     *
     * @param operation The lambda function to perform editing operations on the SharedPreferences.Editor.
     */
    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    /**
     * Extension property for SharedPreferences to manage the city name.
     *
     * Provides a convenient way to get and set the city name value in SharedPreferences.
     */
    var SharedPreferences.cityName: String?
        get() = getString(CITY_NAME, "")
        set(value) {
            editMe {
                it.putString(CITY_NAME, value)
            }
        }
}
