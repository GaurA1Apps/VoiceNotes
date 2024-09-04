package com.app.voicenotesai.presentation.components

import android.content.Context
import android.os.Vibrator
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioRecordBottomSheet(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    sheetState: SheetState,
    onDurationSaved: (Long) -> Unit,
    onAction: (AudioRecordAction) -> Unit
) {

    var isRecording by remember {
        mutableStateOf(false)
    }

    var duration by remember { mutableLongStateOf(0L) }
    var isTimerRunning = sheetState.isVisible
    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

    if (isOpen) {
        ModalBottomSheet(
            containerColor = Color.White,
            modifier = modifier,
            sheetState = sheetState,
            onDismissRequest = {
                isRecording = false
                isTimerRunning = false
                onAction(AudioRecordAction.Dismiss)
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = if (isRecording){
                        stringResource(R.string.recording)
                    } else {
                        stringResource(R.string.record_audio)
                    },
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                )

                // Timer Text
                TimerDisplay(isTimerRunning = isTimerRunning) { elapsedTime ->
                    duration = elapsedTime
                }

                // Button Row
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    AnimatedVisibility(visible = isRecording) {
                        //Close Button
                        RoundButton(
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_close),
                                    contentDescription = "Close",
                                    modifier = Modifier.size(36.dp),
                                    tint = Color.Red
                                )
                            },
                            color = Color.White
                        ) {
                            onAction(AudioRecordAction.Dismiss)
                        }
                    }

                    AnimatedVisibility(visible = !isRecording) {
                        //Record Button
                        RoundButton(icon = {
                            if (isRecording) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_pause),
                                    contentDescription = "Pause",
                                    modifier = Modifier.size(36.dp),
                                    tint = Color.White
                                )
                            } else {
                                FilledCircleIcon()
                            }

                        }) {
                            vibrator?.vibrate(100)
                            if (isRecording) {
                                isRecording = false
                            } else {
                                isRecording = true
                                onAction(AudioRecordAction.StartRecording)
                            }
                        }
                    }

                    AnimatedVisibility(visible = isRecording) {
                        //Save Button
                        RoundButton(
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_done), // Use standard drawable for preview
                                    contentDescription = "Done",
                                    modifier = Modifier.size(36.dp),
                                    tint = Color.Green
                                )
                            },
                            color = Color.White
                        ) {
                            onDurationSaved(duration)
                            onAction(AudioRecordAction.SaveAudio)
                        }
                    }
                }
            }
        }


        isRecording = true
        onAction(AudioRecordAction.StartRecording)
    }
}

sealed class AudioRecordAction {
    data object Dismiss : AudioRecordAction()
    data object StartRecording : AudioRecordAction()
    data object SaveAudio : AudioRecordAction()
}
