package com.app.voicenotesai.player

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlayer(
    private val context: Context
) : AudioPlayer{

    private var player: MediaPlayer? = null

    override fun playFile(filePath: String) {
        MediaPlayer.create(context, filePath.toUri()).apply {
            if (isPlaying) {
                stop()
            }
            setOnCompletionListener {
                stop()
                release()
            }
            player = this
            start()
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    override fun pause() {
        player?.pause()
    }

    override fun resume() {
        player?.start()
    }
}