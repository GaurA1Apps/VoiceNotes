package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.R

@Composable
fun RecordFAB(
    onFabClick: () -> Unit,
) {
    /*FloatingActionButton(
        modifier = Modifier.padding(end = 8.dp, bottom = 16.dp),
        shape = CircleShape,
        onClick = {
            onFabClick()
        },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_mic), contentDescription = "Start Recording")
    }*/

    Box() {
        FloatingActionButton(
            onClick = { onFabClick() },
            shape = CircleShape,
            containerColor = Color.Red,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .size(80.dp)
                .offset(y = 50.dp)
        ) {
            FilledCircleIcon()
        }
    }
}