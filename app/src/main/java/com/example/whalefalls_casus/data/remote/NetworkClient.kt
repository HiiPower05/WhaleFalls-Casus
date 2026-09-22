package com.example.whalefalls_casus.data.remote

import com.example.whalefalls_casus.data.remote.api.TrailApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    // If testing locally on Android Emulator, use 10.0.2.2 instead of localhost
    // Or point to your deployed backend URL
    private const val BASE_URL = "http://10.0.2.2:5000/" // Replace with live URL or http://10.0.2.2:port/

    val trailApiService: TrailApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrailApiService::class.java)
    }
}