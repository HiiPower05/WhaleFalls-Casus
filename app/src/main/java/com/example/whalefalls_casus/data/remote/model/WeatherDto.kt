package com.example.whalefalls_casus.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("location") val locationName: String,
    @SerializedName("temperature_celsius") val temperatureCelsius: Double,
    @SerializedName("condition") val condition: String,
    @SerializedName("wind_speed_kmh") val windSpeedKmh: Double,
    @SerializedName("safety_alert") val safetyAlert: String?
)