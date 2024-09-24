package com.app.voicenotesai.presentation.screens.settings_screen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.voicenotesai.R
import com.app.voicenotesai.presentation.screens.settings_screen.SettingsUtils.getAppVersion
import com.app.voicenotesai.presentation.screens.settings_screen.SettingsUtils.rateUS
import com.app.voicenotesai.presentation.screens.settings_screen.SettingsUtils.shareApp
import com.app.voicenotesai.presentation.theme.grey

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val versionInfo = remember {
        getAppVersion(context)
    }

    Column(
        modifier = Modifier
            .background(grey)
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Text(
            text = "Manage Subscription",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        GeneralSettings(
            iconResId = R.drawable.ic_verssion_info,
            text = versionInfo,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {

        }

        Text(
            text = "Help",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        GeneralSettings(
            iconResId = R.drawable.ic_chat_support,
            text = "Chat with Support",
            modifier = Modifier.padding(bottom = 16.dp)
        ) {

        }

        Text(
            text = "Others",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        GeneralSettings(
            iconResId = R.drawable.ic_verssion_info,
            text = "Rate US"
        ) {
            rateUS(context)
        }
        GeneralSettings(
            iconResId = R.drawable.ic_share,
            text = "Share Flow"
        ) {
            shareApp(context)
        }
        GeneralSettings(
            iconResId = R.drawable.ic_pp_terms_of_use,
            text = "Privacy Policy"
        ) {
//            openUrl(context, context.getString(R.string.privacy_policy_url))
        }
        GeneralSettings(
            iconResId = R.drawable.ic_pp_terms_of_use,
            text = "Terms Of Use"
        ) {
//            openUrl(context, context.getString(R.string.terms_of_use_url))
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
