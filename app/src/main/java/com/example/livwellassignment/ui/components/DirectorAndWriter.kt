package com.example.livwellassignment.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DirectorAndWriter(director: String?, writer: String?) {
    Row {

        Column {
            Text(
                text = "Director",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Text(
                text = "Writers",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = director ?: "",
                fontSize = 16.sp,
                color = Color.White
            )

            writer?.split(",")?.forEach {

                Text(
                    text = it.trim(),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

    }

}