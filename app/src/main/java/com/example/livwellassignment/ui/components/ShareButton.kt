package com.example.livwellassignment.ui.components

import android.content.Intent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShareButton(modifier: Modifier = Modifier, shareText: String) {
    val context = LocalContext.current

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareText)
                }
                val chooser = Intent.createChooser(intent, "Share via")
                context.startActivity(chooser)
            },
            modifier = modifier,
            shape = RoundedCornerShape(50)
        ) {
            Icon(Icons.Default.Share, contentDescription = "Share")
            Text("Share")
        }

}
