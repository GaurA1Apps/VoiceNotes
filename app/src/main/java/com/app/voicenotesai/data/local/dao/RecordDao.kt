package com.app.voicenotesai.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.app.voicenotesai.data.local.entities.AudioRecord
import kotlinx.coroutines.flow.Flow


@Dao
interface RecordDao {

    @Upsert
    suspend fun insertRecord(record: AudioRecord)

    @Query("SELECT * FROM records ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<AudioRecord>>

    @Query("DELETE FROM records WHERE id = :id")
    suspend fun deleteRecord(id: Int)

}