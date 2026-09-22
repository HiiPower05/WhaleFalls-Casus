package com.example.whalefalls_casus.data.remote

import com.example.whalefalls_casus.data.remote.api.TrailApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private const val BASE_URL = "https://api.whalefallscasus.com/" // Replace with actual backend API endpoint when deployed

    val apiService: TrailApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrailApiService::class.java)
    }
}