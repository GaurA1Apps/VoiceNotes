package com.app.voicenotesai.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.voicenotesai.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium.copy( // Adjusting text style
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
        },
        actions = {
            IconButton(onClick = { /* Handle icon click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.voice), // Replace with your app icon
                    contentDescription = "App Icon",
                    modifier = Modifier.size(36.dp)
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}
