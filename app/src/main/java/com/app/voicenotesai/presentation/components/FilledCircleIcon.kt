package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun FilledSquareIcon(
    size: Dp = 28.dp,
    color: Color = Color.White
) {
    Box(
        modifier = Modifier
            .size(size) // Define the size of the square (width and height)
            .background(
                color,
                shape = RoundedCornerShape(4.dp)
            ) // Set the background color for the square
    )
}



@Preview
@Composable
fun PreviewFilledCircleIcon() {
    FilledCircleIcon()
}

@Preview
@Composable
fun PreviewFilledSquareIcon() {
    FilledSquareIcon()
}