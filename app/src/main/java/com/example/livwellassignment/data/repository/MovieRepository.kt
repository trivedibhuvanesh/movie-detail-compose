package com.example.livwellassignment.data.repository

import com.example.livwellassignment.data.api.ApiInterface
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(val apiInterface: ApiInterface)  {
    fun getMovieDetails() = flow {
        emit(apiInterface.getMovieDetails())
    }
}