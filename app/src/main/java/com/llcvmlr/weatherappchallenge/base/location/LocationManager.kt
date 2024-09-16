package com.llcvmlr.weatherappchallenge.base.location


import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import com.llcvmlr.weatherappchallenge.network.ApiCallService
import com.llcvmlr.weatherappchallenge.network.NetWorkConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager

/**
 * LocationManager is responsible for obtaining the user's location and fetching weather data
 * based on the location's latitude and longitude. It uses Google Play Services'
 * `FusedLocationProviderClient` to retrieve the last known location and fetches weather
 * data via an API call using the `weatherApiService`.
 *
 * @property context The application context.
 * @property fusedLocationClient Client used to access the user's location.
 * @property weatherApiService Service responsible for making API calls to get weather data.
 */
class LocationManager @Inject constructor(
    private val context: Context,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val weatherApiService: ApiCallService
) {

    /**
     * Interface used to handle location callbacks. Provides two methods:
     * - `onLocationReceived`: Called when the location is successfully retrieved,
     *   along with the weather response (if available).
     * - `onPermissionDenied`: Called when location permission is denied.
     */
    interface LocationCallback {
        /**
         * Called when the location is retrieved and weather data is fetched.
         *
         * @param location The user's current location.
         * @param weatherResponse The weather data corresponding to the user's location.
         */
        fun onLocationReceived(location: Location?, weatherResponse: WeatherResponse?)

        /**
         * Called when the app lacks the necessary permissions to access the user's location.
         */
        fun onPermissionDenied()
    }

    /**
     * Attempts to obtain the user's last known location and fetches the weather information
     * for that location. The method will invoke the appropriate callback based on whether
     * the location is retrieved and the weather data is successfully fetched.
     *
     * @param callback The callback interface to handle the location and weather data.
     */
    @SuppressLint("MissingPermission")
    fun obtainLocation(callback: LocationCallback) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    // Fetch weather using lat and long
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            // Fetch weather data using latitude and longitude
                            val response = weatherApiService.getWeatherByLocation(
                                latitude = location.latitude,
                                longitude = location.longitude,
                                apiKey = NetWorkConstant.APP_ID
                            ).await()

                            withContext(Dispatchers.Main) {
                                if (response.isSuccessful) {
                                    // Return location and weather data through the callback
                                    callback.onLocationReceived(location, response.body())
                                } else {
                                    // Return null weather data in case of failure
                                    callback.onLocationReceived(location, null)
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            withContext(Dispatchers.Main) {
                                callback.onLocationReceived(location, null)
                            }
                        }
                    }
                } else {
                    callback.onLocationReceived(location, null)
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
                callback.onPermissionDenied()
            }
    }

    /**
     * Checks whether the app has permission to access the user's location. If the app has
     * the necessary permissions, it will proceed to obtain the location. Otherwise, the
     * `onPermissionDenied` callback will be triggered.
     *
     * @param callback The callback to handle location retrieval and permission denial.
     */
    fun checkForPermission(callback: LocationCallback) {
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            callback.onPermissionDenied()
        } else {
            obtainLocation(callback)
        }
    }
}