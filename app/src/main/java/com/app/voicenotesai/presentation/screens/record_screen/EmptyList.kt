package com.app.voicenotesai.presentation.screens.record_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.R

@Composable
fun EmptyList() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.padding(vertical = 8.dp),
            painter = painterResource(R.drawable.ic_no_notes_yet),
            tint = Color.Unspecified,
            contentDescription = "Empty"
        )

        Text(
            text = "No notes yet",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Add new note by clicking below icon",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            style = MaterialTheme.typography.titleLarge
        )
    }
}