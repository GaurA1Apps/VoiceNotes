package com.app.voicenotesai.presentation.screens.record_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.voicenotesai.R
import com.app.voicenotesai.data.local.entities.AudioRecord
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AudioRecordItem(
    audioRecord: AudioRecord,
    onClick: () -> Unit ,
    onTogglePlayback: (Boolean) -> Unit
) {
    val formatter = SimpleDateFormat("MMM dd,yyyy", Locale.getDefault())
    val formattedDate = formatter.format(Date(audioRecord.timestamp))

    Column (
        modifier = Modifier.background(Color.White)
            .clickable { onClick() }
    ){
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0D1D5),
                    contentColor = Color.Red,
                )
            ) {
                Icon(
                    modifier = Modifier.padding(4.dp),
                    painter = painterResource(id = R.drawable.ic_mic),
                    contentDescription = "Microphone Icon"
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = audioRecord.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = formattedDate,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                    )
                    Text(
                        text = formatDuration(audioRecord.durationInSeconds),
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                    )
                }
            }
        }

        HorizontalDivider(color = Color(0xFFE9E4E5))

    }
}

fun formatDuration(durationInSeconds: Long): String {
    val minutes = durationInSeconds / 60
    val seconds = durationInSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Preview
@Composable
fun PreviewAudioRecordItem() {
    AudioRecordItem(
        audioRecord = AudioRecord(
            audioPath = "",
            title = "A Race Against Time: A Summary of Interstellar",
            description = "Sample Description",
            transcript = "Interstellar Movie Summary",
            timestamp = System.currentTimeMillis(),
            durationInSeconds = 7
        ), onTogglePlayback = {

        },
        onClick = {}
    )
}