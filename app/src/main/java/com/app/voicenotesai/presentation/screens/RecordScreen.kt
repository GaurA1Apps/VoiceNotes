package com.app.voicenotesai.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.voicenotesai.player.AndroidAudioPlayer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecordScreen(
    recordingsViewModel: RecordingsViewModel
) {

    val context = LocalContext.current
    val player = AndroidAudioPlayer(context)
    val recordings = recordingsViewModel.recordings.collectAsStateWithLifecycle().value

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
                }
            )
        }
    }
}