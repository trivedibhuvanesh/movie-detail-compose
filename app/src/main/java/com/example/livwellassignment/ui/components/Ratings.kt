package com.example.livwellassignment.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.livwellassignment.data.model.movie_details.response.Ratings

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Ratings(modifier: Modifier = Modifier, ratings: List<Ratings?>?) {
    Box(
        modifier = modifier
    ) {

        FlowRow(
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {

            ratings?.forEach {
                val label = if (it?.source.equals("Internet Movie Database", true)) {
                    "IMDb"
                } else if(it?.source.equals("Rotten Tomatoes", true)) {
                    "RT"
                } else if(it?.source.equals("Metacritic", true)) {
                    "Meta"
                } else {
                    ""
                }

                RatingsButton(text = "$label ${it?.value ?: ""}" ) { }
            }
        }
    }
}