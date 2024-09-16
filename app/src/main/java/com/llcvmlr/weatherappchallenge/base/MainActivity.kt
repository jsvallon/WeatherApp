package com.llcvmlr.weatherappchallenge.base

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.llcvmlr.weatherappchallenge.base.location.LocationManager
import com.llcvmlr.weatherappchallenge.framework.uix.viewmodel.SearchViewModel
import com.llcvmlr.weatherappchallenge.ui.theme.WeatherAppChallenegTheme
import com.llcvmlr.weatherappchallenge.util.WeatherConstant
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * MainActivity is the entry point of the Weather App. It manages the UI and handles location
 * permissions, ensuring the app can retrieve and display weather information based on the
 * user's current location. The activity interacts with the `LocationManager` to fetch
 * location data and communicates with `SearchViewModel` to update the UI.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * LocationManager is injected to handle location retrieval and weather API calls.
     */
    @Inject
    lateinit var locationManager: LocationManager

    /**
     * Launcher for requesting location permissions from the user.
     */
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    /**
     * ViewModel responsible for managing the weather data and business logic of the UI.
     */
    private val viewModel: SearchViewModel by viewModels()

    /**
     * Called when the activity is first created. Sets up the UI and checks for location
     * permissions to fetch the user's current location.
     *
     * @param savedInstanceState The previously saved state of the activity, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize permission launcher for location access
       requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission granted, fetch the location
                viewModel.fetchLocation(locationManager)
            } else {
                // Permission denied, show a toast message
                Toast.makeText(
                    this,
                    WeatherConstant.WEATHER_PERMISSION_DENIED,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Set the main content of the activity using Jetpack Compose
        setContent {
            WeatherAppChallenegTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Entry point of the app's composables, passing the viewModel
                    WeatherEntryApp(viewModel)
                }
            }
        }

        // Check if the location permission is granted when the activity starts
        checkLocationPermission()
    }

    /**
     * Checks if the app has the necessary location permissions. If permission is granted,
     * it fetches the user's location using `LocationManager`. If not, it requests permission
     * from the user.
     */
    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Permission granted, fetch the location
                viewModel.fetchLocation(locationManager)
            }
            else -> {
                // Request permission from the user
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
}

