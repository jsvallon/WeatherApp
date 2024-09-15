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



class LocationManager @Inject constructor(
    private val context: Context,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val weatherApiService: ApiCallService
) {

    interface LocationCallback {
        fun onLocationReceived(location: Location?, weatherResponse: WeatherResponse?)
        fun onPermissionDenied()
    }

    @SuppressLint("MissingPermission")
    fun obtainLocation(callback: LocationCallback) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    // Fetch weather using lat and long
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            // Await the response from the weather API
                            val response = weatherApiService.getWeatherByLocation(
                                latitude = location.latitude,
                                longitude = location.longitude,
                                apiKey = NetWorkConstant.APP_ID  // Replace with your actual API key
                            ).await()  // Use await to get the response

                            withContext(Dispatchers.Main) {
                                if (response.isSuccessful) {
                                    // Pass the WeatherResponse to the callback
                                    callback.onLocationReceived(location, response.body())
                                } else {
                                    // Handle error scenario, e.g., invalid response
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

    fun checkForPermission(callback: LocationCallback) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            callback.onPermissionDenied()
        } else {
            obtainLocation(callback)
        }
    }
}

