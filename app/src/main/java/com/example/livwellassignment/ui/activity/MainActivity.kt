package com.example.livwellassignment.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livwellassignment.ui.viewmodel.MovieViewmodel
import com.example.livwellassignment.utils.UiState
import com.example.livwellassignment.data.model.movie_details.response.MovieDetailsResponse
import com.example.livwellassignment.utils.formatMinutesToHoursMinutes
import com.example.livwellassignment.ui.theme.LivWellAssignmentTheme
import com.example.livwellassignment.ui.components.AddToFavoritesButton
import com.example.livwellassignment.ui.components.CountryAndVotes
import com.example.livwellassignment.ui.components.DirectorAndWriter
import com.example.livwellassignment.ui.components.LoadBlurredImageFromApi
import com.example.livwellassignment.ui.components.PlayTrailer
import com.example.livwellassignment.ui.components.Plot
import com.example.livwellassignment.ui.components.ProfileWithName
import com.example.livwellassignment.ui.components.Ratings
import com.example.livwellassignment.ui.components.ShareButton
import com.example.livwellassignment.ui.components.StatsAndGenre
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val movieViewmodel by viewModels<MovieViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LivWellAssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        //val movieDetails = movieViewmodel.movieDetails.collectAsState().value

                        when (val state = movieViewmodel.movieDetails.collectAsState().value) {

                            is UiState.Loading -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }

                            is UiState.Success -> {
                                val movieDetails = state.data
                                if (movieDetails != null) {
                                    LoadUI(movieDetails)
                                } else {
                                    Toast.makeText(this@MainActivity, "Data is null. Exiting App", Toast.LENGTH_LONG).show()
                                    finish()
                                }
                            }

                            is UiState.Error -> {
                                Toast.makeText(this@MainActivity, "${state.message}. Exiting App", Toast.LENGTH_LONG).show()
                                finish()
                            }

                        }

                    }
                }
            }
        }
    }

    @Composable
    fun LoadUI(movieDetails: MovieDetailsResponse) {

        LoadBlurredImageFromApi(movieDetails.poster ?: "")

        Column(
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = movieDetails.title ?: "",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "${movieDetails.year ?: ""} • ${movieDetails.rated ?: ""} • ${
                    formatMinutesToHoursMinutes(
                        movieDetails.runtime
                    )
                }",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
            )

            AddToFavoritesButton(modifier = Modifier.align(Alignment.End)) {
                Toast.makeText(
                    this@MainActivity,
                    "Add to favorites Click",
                    Toast.LENGTH_SHORT
                ).show()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                PlayTrailer(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    movieDetails.poster ?: ""
                )

                Ratings(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(horizontal = 8.dp),
                    ratings = movieDetails.ratings
                )
            }

            HorizontalDivider(
                color = Color.DarkGray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            StatsAndGenre(
                boxOffice = movieDetails.boxOffice,
                awards = movieDetails.awards,
                genre = movieDetails.genre
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {

                    Plot(movieDetails.plot ?: "")

                    Text(
                        text = "Cast & Crew",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    DirectorAndWriter(
                        director = movieDetails.director,
                        writer = movieDetails.writer
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Cast & Crew >",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    movieDetails.actors?.split(",")?.forEach {
                        ProfileWithName(it.trim())
                    }

                    CountryAndVotes(
                        country = "USA", votes = movieDetails.imdbVotes ?: ""
                    )
                }
            }

            ShareButton(
                shareText = "This is a share button", modifier = Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(top = 8.dp)
            )
        }
    }
}