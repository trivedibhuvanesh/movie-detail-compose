package com.example.livwellassignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.livwellassignment.utils.BlurTransformation

@Composable
fun LoadBlurredImageFromApi(imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .transformations(
                        listOf(
                            BlurTransformation(
                                scale = 0.5f,
                                radius = 10
                            )
                        )
                    )
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 2)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 0f,
                            endY = LocalConfiguration.current.screenHeightDp.toFloat()
                        )
                    )
            )
        }
    }
}