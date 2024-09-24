package com.app.voicenotesai.presentation.screens.record_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.voicenotesai.R

@Composable
fun EmptyList() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(R.drawable.voice),
            contentDescription = "Empty"
        )
        Text(
            text = stringResource(R.string.get_started),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(R.string.flow_transcribe_and_summarizes_recorded_audio_using_ai),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 50.dp, end = 50.dp, top = 8.dp),
            fontSize = 16.sp,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tap the Button to Start Recording",
            modifier = Modifier.padding(vertical = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            style = MaterialTheme.typography.titleMedium
        )
        Image(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .offset(x = 8.dp)
        )
    }
}

@Preview
@Composable
fun EmptyListPreview() {
    EmptyList()
}