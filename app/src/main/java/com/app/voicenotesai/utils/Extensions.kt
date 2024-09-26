package com.app.voicenotesai.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@SuppressLint("DefaultLocale")
fun Long.formatTime(): String {
    val hours = TimeUnit.MILLISECONDS.toHours(this)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this) % 60

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

fun Long.formatDuration(): String {
    val dateFormat =  SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(this))
    return formattedDate
}


fun Long.formatAsTimeOrDate(): String {
    val currentTime = System.currentTimeMillis()
    val timeDifference = currentTime - this

    // 24 hours in milliseconds
    val twentyFourHoursInMillis = 24 * 60 * 60 * 1000

    return if (timeDifference < twentyFourHoursInMillis) {
        // Display time only if recorded in less than 24 hours
        val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        timeFormatter.format(Date(this))
    } else {
        // Display date if recorded more than 24 hours ago
        val dateFormatter = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())
        dateFormatter.format(Date(this))
    }
}

fun extractTitle(response: String): String {
    val titleStart = response.indexOf("Title: ") + "Title: ".length
    val titleEnd = response.indexOf("Summary: ", titleStart)
    return if (titleEnd > titleStart) response.substring(titleStart, titleEnd)
        .trim() else "Title not found"
}

fun extractSummary(response: String): String {
    val summaryStart = response.indexOf("Summary: ") + "Summary: ".length
    val summaryEnd = response.indexOf("Transcript: ", summaryStart)
    return if (summaryEnd > summaryStart) response.substring(summaryStart, summaryEnd)
        .trim() else "Summary not found"
}

fun extractTranscript(response: String): String {
    val transcriptStart = response.indexOf("Transcript: ") + "Transcript: ".length
    return if (transcriptStart > "Transcript: ".length - 1) response.substring(transcriptStart)
        .trim() else "Transcript not found"
}