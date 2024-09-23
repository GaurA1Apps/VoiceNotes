package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FilledCircleIcon(
    size: Dp = 36.dp,
    color: Color = Color.White
) {
    Canvas(modifier = Modifier.size(size)) {
        drawCircle(
            color = color,
            radius = size.toPx() / 2, // Making sure the circle fits within the size
            style = Fill
        )
    }
}

@Preview
@Composable
fun PreviewFilledCircleIcon() {
    FilledCircleIcon()
}