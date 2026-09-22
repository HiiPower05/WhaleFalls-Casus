package com.example.whalefalls_casus.remote

import com.example.whalefalls_casus.data.remote.api.TrailApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: TrailApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrailApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetTrailsResponseParsing() = runBlocking {
        val jsonPayload = """
            [
                {
                    "id": "t1",
                    "name": "Cliff Path",
                    "location": "Walker Bay",
                    "difficulty": "Easy",
                    "distance_km": 5.0,
                    "estimated_duration_hours": 1.5,
                    "description": "Easy ocean walk."
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(jsonPayload).setResponseCode(200))

        val response = apiService.getTrails()
        assertTrue(response.isSuccessful)
        val trails = response.body()
        assertNotNull(trails)
        assertEquals(1, trails?.size)
        assertEquals("Cliff Path", trails?.get(0)?.name)
    }
}