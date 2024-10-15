package com.example.weatherforecastapp.data.model


import com.google.gson.annotations.SerializedName

data class WeatherResponseModel(
    @SerializedName("current")
    var current: Current?,
    @SerializedName("forecast")
    val forecast: Forecast?,
    @SerializedName("location")
    var location: Location?
)