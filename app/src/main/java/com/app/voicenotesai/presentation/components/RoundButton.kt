package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.R

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    iconSize: Dp = 50.dp,
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
        }
    ) {

    }
}

@Preview
@Composable
fun PreviewCloseButton() {
    RoundButton(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "Close",
                modifier = Modifier.size(36.dp),
                tint = Color.Red
            )
        },
        color = Color(0xFFCAAAAA)
    ) {

    }
}

@Preview
@Composable
fun PreviewSaveButton() {
    RoundButton(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_done), // Use standard drawable for preview
                contentDescription = "Done",
                modifier = Modifier.size(36.dp),
                tint = Color.Green
            )
        },
        color = Color(0xFFCBEECD)
    ) {

    }
}