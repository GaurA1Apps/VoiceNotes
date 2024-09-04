package com.app.voicenotesai.domain.repository

import com.app.voicenotesai.data.local.entities.AudioRecord
import kotlinx.coroutines.flow.Flow

interface RecordRepository {

    fun getAllRecords(): Flow<List<AudioRecord>>
    suspend fun insertRecord(record: AudioRecord)
    suspend fun deleteRecord(id: Int)

}