package com.app.voicenotesai.presentation.screens.settings_screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.presentation.theme.bluetheme
import com.app.voicenotesai.presentation.theme.grey
import com.app.voicenotesai.presentation.theme.lightgrey

@Composable
fun GeneralSettings(
    modifier: Modifier = Modifier ,
    iconResId: Int,
    text: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .background(grey, shape = MaterialTheme.shapes.small)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 4.dp),
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "n",
                colorFilter = ColorFilter.tint(bluetheme),
                modifier = Modifier
                    .padding(8.dp)
                    .size(25.dp)
            )
            Text(
                text = text,
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.weight(1f)) // This spacer takes up the remaining space
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow",
                modifier = Modifier.padding(8.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
            thickness = 1.dp,
            color = lightgrey
        )
    }
}

@Preview
@Composable
fun GeneralSettingsPreview() {
    GeneralSettings(
        iconResId = android.R.drawable.ic_menu_info_details,
        text = "General Settings",
        onClick = {}
    )
}