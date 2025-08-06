package com.example.livwellassignment.data.api

import com.example.livwellassignment.data.model.movie_details.response.MovieDetailsResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET("?i=tt3896198&apikey=989d5767")
    suspend fun getMovieDetails() : MovieDetailsResponse

}