package com.example.livwellassignment.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livwellassignment.utils.formatCurrencyCompact

@Composable
fun StatsAndGenre(modifier: Modifier = Modifier, boxOffice: String?, awards: String?, genre: String?) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Stats(
            modifier = Modifier.weight(0.25f),
            label = "Box Office",
            value = formatCurrencyCompact(boxOffice)
        )

        Stats(
            modifier = Modifier.weight(0.25f),
            label = "Awards",
            value = awards ?: ""
        )

        Text(
            text = genre?.replace(",", " • ") ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.weight(0.5f)
        )
    }

}