package com.example.whalefalls_casus.data.remote.api

import com.example.whalefalls_casus.data.remote.model.TrailDto
import com.example.whalefalls_casus.data.remote.model.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrailApiService {


    @GET("trails")
    suspend fun getTrails(): Response<List<TrailDto>>

    @GET("trails/search")
    suspend fun searchTrails(@Query("query") query: String): Response<List<TrailDto>>

    @GET("weather")
    suspend fun getWeatherAlerts(@Query("location") location: String): Response<WeatherDto>
}