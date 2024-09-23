package com.app.voicenotesai.presentation.screens.record_detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.data.local.entities.AudioRecord
import com.app.voicenotesai.presentation.components.StaticWaveform
import com.app.voicenotesai.presentation.theme.grey
import com.app.voicenotesai.utils.getAmplitudes
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AudioRecordDetailScreen(audioRecord: AudioRecord) {
    val context = LocalContext.current
    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy    hh:mm a", Locale.getDefault()) }
    val formattedDate = dateFormat.format(Date(audioRecord.timestamp))
    val coroutineScope = rememberCoroutineScope()
    var isAmplitudeDataAvail by remember { mutableStateOf(false) }
    var amplitudeData by remember { mutableStateOf<List<Int>>(emptyList()) }

    // Fetch amplitude data when the screen is loaded
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            amplitudeData = getAmplitudes(context, audioRecord.audioPath)
            Log.d("AudioRecordDetailScreen", "Amplitude data fetched: $amplitudeData")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .verticalScroll(rememberScrollState()) // Scrollable screen
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = audioRecord.title,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Date",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = formattedDate,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray
            )
        }

        // Divider
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Summary Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Outer padding around the box
                .background(
                    color = Color.White, // Background color (light gray for example)
                    shape = RoundedCornerShape(12.dp) // Rounded corners
                )
                .padding(16.dp) // Inner padding within the box
        ) {
            Text(
                text = audioRecord.description,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Serif,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Outer padding around the box
                .background(
                    color = Color.White, // Background color (light gray for example)
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
                // Divider
                Divider(
                    color = grey,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                // Body (transcript content)
                Text(
                    text = audioRecord.transcript,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

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
            border = BorderStroke(2.dp, Color.Red),
            shape = RoundedCornerShape(8.dp), // Adjust corner radius as needed
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Delete Note")
        }
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
        )
    )
}
