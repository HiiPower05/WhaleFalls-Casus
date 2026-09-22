package com.example.whalefalls_casus.data.repository

import com.example.whalefalls_casus.data.remote.api.TrailApiService
import com.example.whalefalls_casus.data.remote.model.TrailDto
import com.example.whalefalls_casus.data.remote.model.WeatherDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteTrailRepository(private val apiService: TrailApiService) {

    suspend fun fetchAllTrails(): Result<List<TrailDto>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getTrails()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch trails: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchWeather(location: String): Result<WeatherDto> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getWeatherAlerts(location)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch weather: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}