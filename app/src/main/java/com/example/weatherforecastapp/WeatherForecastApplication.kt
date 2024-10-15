package com.example.weatherforecastapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherForecastApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}