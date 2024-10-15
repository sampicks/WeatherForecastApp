package com.example.weatherforecastapp.api

import com.example.weatherforecastapp.JsonProvider
import com.example.weatherforecastapp.data.model.WeatherResponseModel
import com.example.weatherforecastapp.data.network.ApiService
import javax.inject.Inject

class FakeApiService @Inject constructor() : ApiService {
    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false

    override suspend fun getWeatherForecast(
        key: String,
        city: String,
        days: Int,
        aqi: String,
        alerts: String
    ): WeatherResponseModel {
        if (failUserApi) throw Exception("Api failed")
        val fakeResponse: WeatherResponseModel = JsonProvider.objectFromJsonFileWithType(FORECAST_JSON)
        if (wrongResponse) return fakeResponse.apply {
            current = null
            location=null
        }

        return fakeResponse
    }
    companion object {
        private const val FORECAST_JSON = "/json/forecastResponse.json"
    }
}