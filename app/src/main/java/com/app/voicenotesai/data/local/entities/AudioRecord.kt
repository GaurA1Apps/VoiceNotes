package com.app.voicenotesai.data.local.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class AudioRecord(
    val audioPath: String,
    val title: String,
    val description: String,
    val timestamp: Long,
    val durationInSeconds: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Ignore var isPlaying: Boolean = false
}