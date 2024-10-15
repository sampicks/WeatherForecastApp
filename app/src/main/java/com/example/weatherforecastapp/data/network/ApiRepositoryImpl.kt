package com.example.weatherforecastapp.data.network

import com.example.weatherforecastapp.data.model.WeatherResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiRepositoryImpl(private val service: ApiService) : ApiRepository {

    override suspend fun getWeatherForecast(city: String): Flow<WeatherResponseModel> = flow {
        emit(service.getWeatherForecast(city = city))
    }

}