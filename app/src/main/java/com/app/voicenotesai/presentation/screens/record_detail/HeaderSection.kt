package com.app.voicenotesai.presentation.screens.record_detail

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.R
import com.app.voicenotesai.data.local.entities.AudioRecord
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("RememberReturnType")
@Composable
fun HeaderSection(
    audioRecord: AudioRecord,
    onCloseClick: () -> Unit ,
    onShareClick: () -> Unit,
    onFavoriteClickToggle: (Boolean) -> Unit
) {
    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy    hh:mm a", Locale.getDefault()) }
    val formattedDate = dateFormat.format(Date(audioRecord.timestamp))

    // Animate the color based on the favorite state
    val animatedColor by animateColorAsState(
        targetValue = Color.Red , label = ""
    )

    var isFavorite1 by remember { mutableStateOf(false) }
    // Header section with close and share icons
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Delete",
            tint = Color.Blue,
            modifier = Modifier
                .size(24.dp)
                .clickable { onCloseClick() }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "Delete",
            tint = Color.Blue,
            modifier = Modifier.size(24.dp)
                .clickable { onShareClick() }
        )
    }

    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Title
        Text(
            text = audioRecord.title,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 8.dp)
        )

        Icon(
            imageVector = if (isFavorite1) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite Icon",
            tint = animatedColor,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    isFavorite1 = !isFavorite1 // Toggle the state on click
                    onFavoriteClickToggle(isFavorite1)
                }
        )
    }

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
}

@Preview
@Composable
fun HeaderSectionPreview() {
    HeaderSection(
        audioRecord = AudioRecord(
            audioPath = "/path/to/audio",
            title = "Shopping Trip : Trip Planning Begins",
            description = "A quick summary of shopping tasks and discussions.",
            transcript = "We need to buy groceries, check out the new clothing store, and stop by the electronics shop.",
            timestamp = System.currentTimeMillis(),
            durationInSeconds = 90L
        ),
        onCloseClick = {},
        onShareClick = {},
        onFavoriteClickToggle = {}
    )
}
