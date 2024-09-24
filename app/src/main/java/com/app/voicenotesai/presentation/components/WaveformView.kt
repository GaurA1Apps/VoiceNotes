package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WaveformView(
    // List of amplitudes to plot
    amplitudes: List<Int>,
    modifier: Modifier = Modifier,
    color: Color = Color.Blue
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val barWidth = canvasWidth / amplitudes.size
        val gap = 4.dp.toPx() // Adjust this value to control the size of the gap

        amplitudes.forEachIndexed { index, amplitude ->
            val barHeight = amplitude / 100f * canvasHeight // Scale amplitude to fit the canvas
            val xPosition = index * (barWidth + gap) // Adding gap between bars

            drawLine(
                color = color, // You can change this to your preferred color
                start = androidx.compose.ui.geometry.Offset(xPosition, canvasHeight / 2 - barHeight / 2),
                end = androidx.compose.ui.geometry.Offset(xPosition, canvasHeight / 2 + barHeight / 2),
                strokeWidth = barWidth
            )
        }
    }
}

@Preview
@Composable
fun WaveformDemo() {
    WaveformView(
        amplitudes = List(50) { (0..100).random() },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        color = Color.Red
    )
}
