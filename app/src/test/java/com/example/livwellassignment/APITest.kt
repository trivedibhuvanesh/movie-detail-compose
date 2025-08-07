package com.example.livwellassignment

import com.example.livwellassignment.data.api.ApiInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class APITest {

    lateinit var mockWebServer: MockWebServer
    lateinit var api: ApiInterface

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(ApiInterface::class.java)
    }

    @Test
    fun getMovieDetailsHardCoded() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("{ \"Title\": \"BT\" }")
        mockWebServer.enqueue(mockResponse)

        val response = api.getMovieDetails()
        mockWebServer.takeRequest()

        Assert.assertEquals("BT", response.title)
    }

    @Test
    fun getMovieDetailsFromHardCodedFile() = runTest {
        val mockResponse = MockResponse()
        val content = FileReader.readFileResource("/getMovieDetailsApiMockResponse.json")
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = api.getMovieDetails()
        mockWebServer.takeRequest()

        Assert.assertEquals("Bhuvanesh Trivedi", response.director)
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }
}