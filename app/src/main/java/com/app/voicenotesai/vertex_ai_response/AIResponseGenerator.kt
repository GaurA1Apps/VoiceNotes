package com.app.voicenotesai.vertex_ai_response

import android.content.ContentResolver
import android.net.Uri
import com.app.voicenotesai.MainActivity.Companion.generativeModel
import com.app.voicenotesai.utils.Constants.PROMPT
import com.google.firebase.vertexai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AIResponseGenerator(
    private val contentResolver: ContentResolver
) {

    suspend fun getTranscription(audioUri: Uri): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                // Open the audio file as an input stream
                contentResolver.openInputStream(audioUri).use { stream ->
                    stream?.let {
                        val audioBytes = stream.readBytes()

                        // Create the prompt for the AI model
                        val prompt = content {
                            blob("audio/mpeg", audioBytes) // Assuming audio is in MP3 format
                            text(PROMPT)
                        }

                        val response = generativeModel.generateContent(prompt)

                        // Return success with the generated text or fallback message
                        return@withContext Result.success(response.text ?: "No text generated")
                    } ?: return@withContext Result.failure(Exception("Failed to open audio stream"))
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }
    }
}
