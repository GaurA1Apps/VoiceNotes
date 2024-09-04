package com.app.voicenotesai.player

import java.io.File

interface AudioPlayer {

    fun playFile(filePath: String)
    fun stop()
    fun pause()
    fun resume()

}