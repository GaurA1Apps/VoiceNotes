package com.app.voicenotesai.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.app.voicenotesai.data.local.entities.AudioRecord
import com.app.voicenotesai.navigation.NavGraphSetup
import com.app.voicenotesai.presentation.components.AudioRecordAction
import com.app.voicenotesai.presentation.components.AudioRecordBottomSheet
import com.app.voicenotesai.presentation.components.BottomNavigationBar
import com.app.voicenotesai.presentation.components.FilledCircleIcon
import com.app.voicenotesai.presentation.components.RecordFAB
import com.app.voicenotesai.presentation.components.TopBar
import com.app.voicenotesai.presentation.screens.record_screen.RecordingsViewModel
import com.app.voicenotesai.recorder.AndroidAudioRecorder
import com.app.voicenotesai.utils.PermissionUtils
import com.app.voicenotesai.utils.extractSummary
import com.app.voicenotesai.utils.extractTitle
import com.app.voicenotesai.utils.extractTranscript
import com.app.voicenotesai.vertex_ai_response.AIResponseGenerator
import kotlinx.coroutines.launch
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val TAG = "MyApp"
    val navController = rememberNavController()
    val contentResolver = navController.context.contentResolver
    val aiResponseGenerator = AIResponseGenerator(contentResolver)
    val permissionUtils = PermissionUtils(navController.context)
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isRecordBottomSheetOpen by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    //Injecting ViewModel
    val recordingsViewModel: RecordingsViewModel = hiltViewModel()

    //Audio Recorder and Player
    val recorder by lazy { AndroidAudioRecorder(navController.context) }
    var audioFile: File? = null

    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            /*RecordFAB {
                // Handle FAB click
                if (permissionUtils.hasRecordAudioPermission()) {
                    isRecordBottomSheetOpen = true
                } else {
                    permissionUtils.requestRecordAudioPermission()
                }
            }*/

            Box() {
                FloatingActionButton(
                    onClick = { /* stub */ },
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
    ) { innerPadding ->
        NavGraphSetup(navController = navController, innerPadding, recordingsViewModel)

        //Bottom Sheet for recording
        if (isRecordBottomSheetOpen) {
            AudioRecordBottomSheet(
                modifier = Modifier.safeContentPadding(),
                isOpen = isRecordBottomSheetOpen,
                sheetState = sheetState,
                onDurationSaved = {
                    Log.d("AudioRecordBottomSheet", "Duration saved: $it")
                },
                onAction = { action ->
                    when (action) {
                        is AudioRecordAction.Dismiss -> {
                            recorder.stop()
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    isRecordBottomSheetOpen = false
                                }
                            }
                        }

                        is AudioRecordAction.StartRecording -> {
                            //Voice to Text

                            // Handle Audio Recording
                            File(
                                navController.context.externalCacheDir?.absolutePath,
                                "audio_${System.currentTimeMillis()}.mp3"
                            ).also {
                                recorder.start(it)
                                audioFile = it
                            }
                        }

                        is AudioRecordAction.SaveAudio -> {
                            scope.launch {
                                recorder.stop()

                                // Handle closing the bottom sheet
                                scope.launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        isRecordBottomSheetOpen = false
                                    }
                                }

                                isLoading = true

                                val result =
                                    aiResponseGenerator.getTranscription(audioFile!!.toUri())

                                if (result.isSuccess) {
                                    val response = result.getOrThrow()
                                    Log.d(TAG, "Generated Text: $response")

                                    Log.d("Title", extractTitle(response))
                                    Log.d("Summary", extractSummary(response))
                                    Log.d("Transcript", extractTranscript(response))

                                    // Handle Audio Saving
                                    val record = AudioRecord(
                                        audioPath = audioFile!!.absolutePath ?: "",
                                        title = extractTitle(response),
                                        description = extractSummary(response),
                                        transcript = extractTranscript(response),
                                        timestamp = System.currentTimeMillis(),
                                        durationInSeconds = 8
                                    )
                                    recordingsViewModel.insertRecord(record)
                                } else {
                                    Log.e(
                                        TAG,
                                        "Error generating AI response: ${result.exceptionOrNull()}"
                                    )
                                }
                                isLoading = false
                            }
                        }
                    }
                }
            )
        }

        if (isLoading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.7f)) // Semi-transparent background
            ) {
                CircularProgressIndicator() // Show loading spinner
                Spacer(modifier = Modifier.height(16.dp))
                Text("Transcribing audio, please wait...", fontSize = 16.sp)
            }
        }
    }
}

