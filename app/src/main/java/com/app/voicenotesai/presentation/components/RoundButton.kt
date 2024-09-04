package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    iconSize: Dp = 55.dp,
    color: Color = Color.Red,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(iconSize)
            .background(color = color, shape = CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}

@Preview
@Composable
fun PreviewOFButton() {
    RoundButton(
        icon = {
            FilledCircleIcon()
        },
        iconSize = 60.dp,
    ) {

    }
}