package com.app.voicenotesai.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.app.voicenotesai.data.local.entities.AudioRecord
import com.app.voicenotesai.navigation.NavGraphSetup
import com.app.voicenotesai.presentation.components.AudioRecordAction
import com.app.voicenotesai.presentation.components.AudioRecordBottomSheet
import com.app.voicenotesai.presentation.components.BottomNavigationBar
import com.app.voicenotesai.presentation.components.RecordFAB
import com.app.voicenotesai.presentation.components.TopBar
import com.app.voicenotesai.recorder.AndroidAudioRecorder
import com.app.voicenotesai.utils.PermissionUtils
import com.app.voicenotesai.voice_to_text.VoiceToTextParser.Companion.TAG
import kotlinx.coroutines.launch
import java.io.File
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val permissionUtils = PermissionUtils(navController.context)
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isRecordBottomSheetOpen by remember { mutableStateOf(false) }

    //Injecting ViewModel
    val recordingsViewModel: RecordingsViewModel = hiltViewModel()

    //Audio Recorder and Player
    val recorder by lazy { AndroidAudioRecorder(navController.context) }
    var audioFile: File? = null

    /*val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(navController.context)
    val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    val voiceToTextParser by lazy { VoiceToTextParser(application) }
    val state by voiceToTextParser.state.collectAsStateWithLifecycle()
    startSpeechToText(navController.context, speechRecognizerIntent, speechRecognizer, onTextChanged = {
        newText = it
    })*/

    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        floatingActionButton = {
            RecordFAB {
                // Handle FAB click
                if (permissionUtils.hasRecordAudioPermission()) {
                    isRecordBottomSheetOpen = true
                } else {
                    permissionUtils.requestRecordAudioPermission()
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
                                scope.launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        isRecordBottomSheetOpen = false
                                    }
                                }

                                /*val textObtained = state.spokenText.ifEmpty {
                                    state.error ?: "Error"
                                }*/
                                // Handle Audio Saving
                                val record = AudioRecord(
                                    audioPath = audioFile?.absolutePath ?: "",
                                    title = "Audio File Title",
                                    description = "Sample Description",
                                    timestamp = System.currentTimeMillis(),
                                    durationInSeconds = 8
                                )
                                recordingsViewModel.insertRecord(record)
                            }
                        }
                    }
                }
            )
        }
    }
}

private fun startSpeechToText(
    context: Context,
    speechRecognizerIntent: Intent,
    speechRecognizer: SpeechRecognizer,
    onTextChanged: (String) -> Unit
) {
    speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)
    speechRecognizer.setRecognitionListener(object : RecognitionListener {
        override fun onReadyForSpeech(bundle: Bundle) {}

        override fun onBeginningOfSpeech() {
            Log.d(TAG, "Beginning of speech")
        }

        override fun onRmsChanged(v: Float) {}

        override fun onBufferReceived(bytes: ByteArray) {
            Log.d(TAG, "BufferRecieved: $bytes")
        }

        override fun onEndOfSpeech() {
            Log.d(TAG, "End of speech.")
        }

        override fun onError(i: Int) {
            Log.d(TAG, "Error: $i")
        }

        override fun onResults(bundle: Bundle) {
            val matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (matches != null && matches.isNotEmpty()) {
                onTextChanged(matches[0])
            }
            Log.d(TAG, "onResults: $matches")
        }

        override fun onPartialResults(partialResults: Bundle) {
            val partialMatches = partialResults.getStringArrayList(RecognizerIntent.EXTRA_PARTIAL_RESULTS)
            if (partialMatches != null && partialMatches.isNotEmpty()) {
                onTextChanged(partialMatches[0])
            }
            Log.d(TAG, "onPartialResults: $partialMatches")
        }

        override fun onEvent(i: Int, bundle: Bundle) {}
    })
}