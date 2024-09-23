package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StaticWaveform(amplitudeData: List<Int>, modifier: Modifier = Modifier) {
    // Define color and bar width for the waveform
    val waveformColor = Color(0xFF4285F4) // Replace with your desired color
    val barWidth = 6.dp // Width of each bar
    val barSpacing = 4.dp // Space between each bar

    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(100.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val maxAmplitude = amplitudeData.maxOrNull() ?: 1

        // Calculate scaling factor to fit waveform within the canvas height
        val amplitudeScale = canvasHeight / (maxAmplitude * 2)

        amplitudeData.forEachIndexed { index, amplitude ->
            val xOffset = index * (barWidth.toPx() + barSpacing.toPx())
            if (xOffset > canvasWidth) return@forEachIndexed // Prevent drawing outside the canvas

            // Draw vertical bars representing the waveform
            drawLine(
                color = waveformColor,
                start = androidx.compose.ui.geometry.Offset(
                    xOffset,
                    canvasHeight / 2 - amplitude * amplitudeScale
                ),
                end = androidx.compose.ui.geometry.Offset(
                    xOffset,
                    canvasHeight / 2 + amplitude * amplitudeScale
                ),
                strokeWidth = barWidth.toPx(),
                cap = Stroke.DefaultCap
            )
        }
    }
}

@Preview
@Composable
fun WaveformPreview() {
    val amplitudeData =
        listOf(3, 6, 9, 12, 9, 6, 3, 1, 3, 6, 10, 12, 10, 6, 3, 2, 4, 6, 1) // Example data
    StaticWaveform(amplitudeData = amplitudeData)
}


