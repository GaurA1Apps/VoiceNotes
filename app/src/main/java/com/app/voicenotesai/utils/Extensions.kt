package com.app.voicenotesai.utils

import android.annotation.SuppressLint
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import linc.com.amplituda.Amplituda
import linc.com.amplituda.Cache
import linc.com.amplituda.callback.AmplitudaErrorListener
import linc.com.amplituda.exceptions.AmplitudaException
import java.util.concurrent.TimeUnit

@SuppressLint("DefaultLocale")
fun Long.formatTime(): String {
    val hours = TimeUnit.MILLISECONDS.toHours(this)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this) % 60

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

// Function to get the amplitude data
suspend fun getAmplitudes(context: Context,path: String): List<Int> = withContext(Dispatchers.IO) {
    val amplituda = Amplituda(context)
    return@withContext amplituda.processAudio(path, Cache.withParams(Cache.REUSE))
        .get(AmplitudaErrorListener {
            it.printStackTrace()
        })
        .amplitudesAsList()
}

fun extractTitle(response: String): String {
    val titleStart = response.indexOf("Title: ") + "Title: ".length
    val titleEnd = response.indexOf("Summary: ", titleStart)
    return if (titleEnd > titleStart) response.substring(titleStart, titleEnd).trim() else "Title not found"
}

fun extractSummary(response: String): String {
    val summaryStart = response.indexOf("Summary: ") + "Summary: ".length
    val summaryEnd = response.indexOf("Transcript: ", summaryStart)
    return if (summaryEnd > summaryStart) response.substring(summaryStart, summaryEnd).trim() else "Summary not found"
}

fun extractTranscript(response: String): String {
    val transcriptStart = response.indexOf("Transcript: ") + "Transcript: ".length
    return if (transcriptStart > "Transcript: ".length - 1) response.substring(transcriptStart).trim() else "Transcript not found"
}