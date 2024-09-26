package com.app.voicenotesai.presentation.components

import androidx.compose.animation.AnimatedContent
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
    modifier: Modifier = Modifier,
    onPlayAudio: (Boolean) -> Unit
) {

    var isPlaying by remember { mutableStateOf(false) }

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
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedContent(targetState = isPlaying, label = "") { playingState ->
                if (playingState) {
                    // Pause button
                    IconButton(onClick = {
                        isPlaying = false // Update the state here
                        onPlayAudio(isPlaying)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pause_round),
                            contentDescription = "Pause Button",
                            tint = Color.Blue,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                } else {
                    // Play button
                    IconButton(onClick = {
                        isPlaying = true // Update the state here
                        onPlayAudio(isPlaying)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play_round),
                            contentDescription = "Play Button",
                            tint = Color.Blue,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }

            WaveformView(
                amplitudes = List(50) { (0..100).random() }, // Sample data, replace with real amplitudes
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .height(24.dp)
            )

            Text(
                text = "00:04", // Update dynamically
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun AudioCardViewPreview() {
    AudioWaveformCard(
        onPlayAudio = { isPlaying ->
            // Handle audio playback here
        }
    )
}