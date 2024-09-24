package com.app.voicenotesai.presentation.components

import android.content.Context
import android.os.Vibrator
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.R
import com.app.voicenotesai.presentation.theme.grey


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

    var isPaused by remember {
        mutableStateOf(false)
    }
    var duration by remember { mutableLongStateOf(0L) }
    var isTimerRunning = sheetState.isVisible
    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

    if (isOpen) {
        isRecording = true
        onAction(AudioRecordAction.StartRecording)

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
                    text = if (isRecording) {
                        stringResource(R.string.recording)
                    } else {
                        stringResource(R.string.record_audio)
                    },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                )

                // Timer Text
                TimerDisplay(
                    isTimerRunning = isTimerRunning,
                    isPaused = isPaused,
                    onTimerStop = { totalTime ->
                        // Handle timer stop event, totalTime is the elapsed time
                        Log.d("AudioRecordBottomSheet","Timer stopped at: $totalTime seconds")
                    }
                )

                // Button Row
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                            color = grey
                        ) {
                            onAction(AudioRecordAction.Dismiss)
                        }

                        Text(
                            text = "Cancel",
                            color = Color.Black,
                            fontWeight = FontWeight.Light,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        )
                    }


                    //Record Button
                    /*RoundButton(icon = {
                        if (isRecording) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_pause),
                                contentDescription = "Pause",
                                modifier = Modifier.size(36.dp),
                                tint = Color.White
                            )
                        } else {
                            FilledSquareIcon()
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
*/

                    //Save Button
                    FloatingActionButton(
                        onClick = {
                            vibrator?.vibrate(100)
                            onDurationSaved(duration)
                            onAction(AudioRecordAction.SaveAudio)
                        },
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        shape = CircleShape,
                        modifier = Modifier
                            .size(76.dp)
                            .offset(y = (-4).dp)
                    ) {
                        FilledSquareIcon()
                    }


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //Save Button
                        RoundButton(
                            icon = {
                                Icon(
                                    painter = if (isPaused) {
                                        painterResource(id = R.drawable.ic_play)
                                    } else {
                                        painterResource(id = R.drawable.ic_pause)
                                    }, // Use standard drawable for preview
                                    contentDescription = "Done",
                                    modifier = Modifier.size(36.dp),
                                    tint = Color.Red
                                )
                            },
                            color = grey
                        ) {
                            isPaused = !isPaused
                            onAction(if (isPaused) AudioRecordAction.PauseRecording else AudioRecordAction.ResumeRecording)
                        }

                        Text(
                            text = if (isPaused) "Resume" else "Pause",
                            color = Color.Black,
                            fontWeight = FontWeight.Light,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AudioRecordBottomSheetPreview() {
    AudioRecordBottomSheet(
        isOpen = true,
        sheetState = rememberModalBottomSheetState(),
        onDurationSaved = {},
        onAction = {},
    )
}

sealed class AudioRecordAction {
    data object Dismiss : AudioRecordAction()
    data object StartRecording : AudioRecordAction()
    data object SaveAudio : AudioRecordAction()
    data object PauseRecording : AudioRecordAction()
    data object ResumeRecording : AudioRecordAction()
}
