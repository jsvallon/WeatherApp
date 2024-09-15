package com.llcvmlr.weatherappchallenge.base

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.llcvmlr.weatherappchallenge.util.ModelPreferencesManager
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        ModelPreferencesManager.with(this)
        //location
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "location",
                "location",
                NotificationManager.IMPORTANCE_LOW
            )

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
