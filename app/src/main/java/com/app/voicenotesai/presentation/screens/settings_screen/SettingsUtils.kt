package com.app.voicenotesai.presentation.screens.settings_screen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast

object SettingsUtils {


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
            Toast.makeText(context, "Google Play Store not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openUrl(context: Context, url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.setData(Uri.parse(url))
        context.startActivity(i)
    }

    fun getAppVersion(context: Context): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val versionName = packageInfo.versionName
        val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode
        } else {
            packageInfo.versionCode.toLong()
        }
        return "Version $versionName, Build $versionCode"
    }
}