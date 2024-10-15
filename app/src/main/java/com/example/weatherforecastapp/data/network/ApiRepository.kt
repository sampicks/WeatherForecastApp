package com.example.weatherforecastapp.data.network

import com.example.weatherforecastapp.data.model.WeatherResponseModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun getWeatherForecast(city: String): Flow<WeatherResponseModel>
}