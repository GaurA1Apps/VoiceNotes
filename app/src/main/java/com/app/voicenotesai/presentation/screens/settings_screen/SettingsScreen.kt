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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.app.voicenotesai.R
import com.app.voicenotesai.presentation.theme.grey

@Composable
fun SettingsScreen(paddingValues: PaddingValues) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(grey)
            .fillMaxSize()
    ) {
        GeneralSettings(
            iconResId = R.drawable.ic_rate_us,
            text = "Rate US"
        ) {
            rateUS(context)
        }
        GeneralSettings(
            iconResId = R.drawable.ic_share_app,
            text = "Share App"
        ) {
            shareApp(context)
        }
        GeneralSettings(
            iconResId = R.drawable.ic_help_support,
            text = "Help & Support"
        ) {
//            openUrl(context, context.getString(R.string.help_support_url))
        }
        GeneralSettings(
            iconResId = R.drawable.ic_privacy_policy,
            text = "Privacy Policy"
        ) {
//            openUrl(context, context.getString(R.string.privacy_policy_url))
        }
        GeneralSettings(
            iconResId = R.drawable.ic_terms_of_use,
            text = "Terms Of Use"
        ) {
//            openUrl(context, context.getString(R.string.terms_of_use_url))
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(paddingValues = PaddingValues())
}

fun shareApp(context: Context) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "Check out this app: https://play.google.com/store/apps/details?id=${context.packageName}"
        )
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share App"))
}

fun rateUS(context: Context) {
    val appPackageName = context.packageName
    val rateIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
    try {
        context.startActivity(rateIntent)
    } catch (e: ActivityNotFoundException) {
        // Handle the error if Google Play Store is not installed
        Toast.makeText(context, "Google Play Store not found", Toast.LENGTH_SHORT).show()
    }
}

private fun openUrl(context: Context, url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.setData(Uri.parse(url))
    context.startActivity(i)
}