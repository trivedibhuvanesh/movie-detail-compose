package com.example.livwellassignment.repository

import com.example.livwellassignment.FileReader
import com.example.livwellassignment.data.api.ApiInterface
import com.example.livwellassignment.data.model.movie_details.response.MovieDetailsResponse
import com.example.livwellassignment.data.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieRepositoryTest {

    @Mock
    lateinit var apiInterface: ApiInterface

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun movieRepoTest() = runTest {
        val content = FileReader.readFileResource("/getMovieDetailsApiMockResponse.json")

        val movieDetailsResponse: MovieDetailsResponse? = FileReader.stringToJson(content)
        Mockito.`when`(apiInterface.getMovieDetails()).thenReturn(movieDetailsResponse)

        val movieRepository = MovieRepository(apiInterface)
        val result = movieRepository.getMovieDetails()

        Assert.assertEquals("Bhuvanesh Trivedi", result.first().director)
        print(result.first())

    }
}