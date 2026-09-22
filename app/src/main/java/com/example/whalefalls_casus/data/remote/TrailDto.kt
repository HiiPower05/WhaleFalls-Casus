package com.example.whalefalls_casus.data.remote.model

import com.google.gson.annotations.SerializedName

data class TrailDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("location") val location: String,
    @SerializedName("difficulty") val difficulty: String,
    @SerializedName("distance_km") val distanceKm: Double,
    @SerializedName("estimated_duration_hours") val estimatedDurationHours: Double,
    @SerializedName("description") val description: String
)