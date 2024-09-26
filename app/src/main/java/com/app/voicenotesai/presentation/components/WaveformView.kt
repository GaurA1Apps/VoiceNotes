package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WaveformView(
    amplitudes: List<Int>,
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    gapDp: Dp = 2.dp // customizable gap between the bars
) {
    val gapPx = with(LocalDensity.current) { gapDp.toPx() }

    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val totalGaps = (amplitudes.size - 1) * gapPx
        val barWidth = (canvasWidth - totalGaps) / amplitudes.size

        amplitudes.forEachIndexed { index, amplitude ->
            // Scale amplitude and ensure it fits in the height
            val barHeight = (amplitude / 100f) * canvasHeight
            val xPosition = index * (barWidth + gapPx)

            drawLine(
                color = color,
                start = Offset(xPosition, canvasHeight / 2 - barHeight / 2),
                end = Offset(xPosition, canvasHeight / 2 + barHeight / 2),
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
