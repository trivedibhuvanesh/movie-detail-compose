package com.example.livwellassignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livwellassignment.data.repository.MovieRepository
import com.example.livwellassignment.utils.UiState
import com.example.livwellassignment.data.model.movie_details.response.MovieDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewmodel @Inject constructor(movieRepository: MovieRepository): ViewModel() {

    private val _movieDetails = MutableStateFlow<UiState<MovieDetailsResponse?>>(UiState.Loading)
    val movieDetails: StateFlow<UiState<MovieDetailsResponse?>>
        get() = _movieDetails

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                movieRepository.getMovieDetails().collect{
                    _movieDetails.value = UiState.Success(it)
                }
            } catch (e:Exception) {
                _movieDetails.value = UiState.Error(e.message ?: "")
            }
        }
    }
}