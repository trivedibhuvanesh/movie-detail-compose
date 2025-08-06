package com.example.livwellassignment.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun Stats(label: String, value: String, modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Gray
        )

        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}