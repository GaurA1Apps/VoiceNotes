package com.app.voicenotesai.presentation.screens.record_detail

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.app.voicenotesai.data.local.entities.AudioRecord
import com.app.voicenotesai.player.AndroidAudioPlayer
import com.app.voicenotesai.presentation.components.AudioWaveformCard
import com.app.voicenotesai.presentation.theme.grey
import com.app.voicenotesai.presentation.theme.lightgrey
import java.io.File

@Composable
fun AudioRecordDetailScreen(
    audioRecord: AudioRecord,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val player by lazy { AndroidAudioPlayer(context) }

    BackHandler {
        onBackClick()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightgrey)
                .padding(horizontal = 16.dp)
        ) {
            HeaderSection(
                audioRecord,
                onShareClick = {

                },
                onFavoriteClickToggle = { isFavorite ->

                },
                onCloseClick = {
                    onBackClick()
                },
            )

            // Scrollable content for Summary and Transcript
            LazyColumn(
                modifier = Modifier // Takes up remaining space and becomes scrollable
                    .fillMaxSize()
                    .padding(bottom = 120.dp)
            ) {

                // Summary Section
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(
                                color = Color.White, // Background color (light gray for example)
                                shape = RoundedCornerShape(12.dp) // Rounded corners
                            )
                            .padding(16.dp) // Inner padding within the box
                    ) {
                        Text(
                            text = audioRecord.description,
                            fontWeight = FontWeight.Normal,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }

                // Transcript Section
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(
                                color = Color.White, // Background color
                                shape = RoundedCornerShape(12.dp) // Rounded corners
                            )
                            .padding(16.dp) // Inner padding within the box
                    ) {
                        Column {
                            // Heading
                            Text(
                                text = "Transcript",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Divider(
                                color = grey,
                                thickness = 2.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            Text(
                                text = audioRecord.transcript,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                item {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Red
                        ),
                        border = BorderStroke(2.dp, Color.LightGray),
                        shape = RoundedCornerShape(8.dp), // Adjust corner radius as needed
                    ) {
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
                        Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Delete Note")
                    }
                }
            }
        }

        AudioWaveformCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .background(grey.copy(alpha = 0.2f))
                .fillMaxWidth(),
            onPlayAudio = { isPlaying ->
                val audioFile = File(audioRecord.audioPath)
                if (audioFile.exists()) {
                    val uri = FileProvider.getUriForFile(
                        context,
                        "com.app.voicenotesai.fileprovider",
                        audioFile
                    )
                    player.playFile(uri.toString()) // Use the content URI
                } else {
                    // Handle file not found error
                    Toast.makeText(context, "Audio file not found", Toast.LENGTH_SHORT).show()
                }
            }

        )
    }
}


@Preview
@Composable
fun AudioRecordDetailScreenPreview() {
    // Create a sample AudioRecord for preview
    AudioRecordDetailScreen(
        audioRecord = AudioRecord(
            audioPath = "/path/to/audio",
            title = "Shopping Trip : Trip Planning Begins",
            description = "A quick summary of shopping tasks and discussions.",
            transcript = "We need to buy groceries, check out the new clothing store, and stop by the electronics shop.",
            timestamp = System.currentTimeMillis(),
            durationInSeconds = 90L
        ),
        onBackClick = {}
    )
}
