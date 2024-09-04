package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.R

@Composable
fun RecordFAB(
    onFabClick: () -> Unit,
) {
    FloatingActionButton(
        modifier = Modifier.padding(end = 8.dp, bottom = 16.dp),
        shape = CircleShape,
        onClick = {
            onFabClick()
        },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_mic), contentDescription = "Start Recording")
    }
}