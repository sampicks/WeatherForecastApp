package com.example.weatherforecastapp.data.network

import com.example.weatherforecastapp.data.model.WeatherResponseModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
   suspend fun getWeatherForecast(
        @Query("key") key: String = ApiConfig.API_KEY,
        @Query("q") city: String,
        @Query("days") days: Int = 5,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): WeatherResponseModel
}