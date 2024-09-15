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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var locationManager: LocationManager

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize permission launcher
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                viewModel.fetchLocation(locationManager)
            } else {
                Toast.makeText(this, WeatherConstant.WEATHER_PERMISSION_DENIED, Toast.LENGTH_SHORT).show()
            }
        }

        setContent {
            WeatherAppChallenegTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherEntryApp(viewModel)
                }
            }
        }

        // Check for location permission
        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.fetchLocation(locationManager)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
}
