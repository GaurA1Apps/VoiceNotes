package com.app.voicenotesai.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@SuppressLint("DefaultLocale")
@Composable
fun TimerDisplay(
    modifier: Modifier = Modifier,
    isTimerRunning: Boolean,
    isPaused: Boolean,
    onTimerStop: (Long) -> Unit
) {
    var timeInSeconds by remember { mutableLongStateOf(0L) }
    val timeString = String.format(
        "%02d:%02d:%02d",
        (timeInSeconds / 3600),
        (timeInSeconds % 3600) / 60,
        (timeInSeconds % 60)
    )

    LaunchedEffect(isTimerRunning,isPaused) {
        if (isTimerRunning && !isPaused) {
            while (true) {
                delay(1000L)
                timeInSeconds++
            }
        } else if (!isTimerRunning) {
            onTimerStop(timeInSeconds)
            timeInSeconds = 0L
        }
    }

    Text(
        text = timeString,
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(bottom = 16.dp)
    )
}

@Preview
@Composable
fun TimerDisplayPreview() {
    TimerDisplay(isTimerRunning = true, isPaused = false, onTimerStop = {})
}