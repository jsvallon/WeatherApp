package com.llcvmlr.weatherappchallenge.util

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val CITY_NAME: String = "cityName"

    fun customPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val key = pair.first
        when (val value = pair.second) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in SharedPreferences")
        }
    }

    var SharedPreferences.cityName: String?
        get() = getString(CITY_NAME, "")
        set(value) {
            editMe {
                it.putString(CITY_NAME, value)
            }
        }
}