package com.app.voicenotesai.data.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Entity(tableName = "records")
data class AudioRecord(
    val audioPath: String,
    val title: String,
    val description: String,
    val transcript: String,
    val timestamp: Long,
    val durationInSeconds: Long,
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @IgnoredOnParcel
    @Ignore var isPlaying: Boolean = false
}