package com.app.voicenotesai.presentation.screens.record_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.voicenotesai.data.local.entities.AudioRecord
import com.app.voicenotesai.player.AndroidAudioPlayer

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecordScreen(
    recordingsViewModel: RecordingsViewModel,
    onItemClick: (AudioRecord) -> Unit
) {

    val context = LocalContext.current
    val player = AndroidAudioPlayer(context)
    val recordings = recordingsViewModel.recordings.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (recordings.isEmpty()) {
            EmptyList()

        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(recordings) { recording ->
                    AudioRecordItem(audioRecord = recording,
                        onTogglePlayback = { isPlaying ->
                            if (!isPlaying) {
                                player.playFile(recording.audioPath)
                                recording.isPlaying = true
                            } else {
                                player.stop()
                                recording.isPlaying = false
                            }
                        },
                        onClick = {
                            onItemClick(recording)
                        }
                    )
                }
            }
        }
    }
}