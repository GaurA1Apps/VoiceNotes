package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.voicenotesai.R

@Composable
fun AudioWaveformCard(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Play button
            IconButton(onClick = { /* Handle Play Button click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play_round), // Your play icon resource
                    contentDescription = "Play Button",
                    tint = Color.Blue,
                    modifier = Modifier.size(36.dp)
                )
            }

            // Waveform visualization
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .height(36.dp)
            ) {
                WaveformView(
                    amplitudes = List(50) { (0..100).random() }, // Sample data, replace with real amplitudes
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Red
                )

                // Timer
                Text(
                    text = "00:04", // Update dynamically
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.CenterEnd) // Aligns the timer to the end of the Box
                        .padding(end = 8.dp) // Optional padding from the end
                )
            }
        }
    }
}

@Preview
@Composable
fun AudioCardViewPreview() {
    AudioWaveformCard()
}