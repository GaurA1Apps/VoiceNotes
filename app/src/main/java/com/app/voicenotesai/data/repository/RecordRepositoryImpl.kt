package com.app.voicenotesai.data.repository

import com.app.voicenotesai.data.local.dao.RecordDao
import com.app.voicenotesai.data.local.entities.AudioRecord
import com.app.voicenotesai.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl(
    private val recordDao: RecordDao
) : RecordRepository {


    override fun getAllRecords(): Flow<List<AudioRecord>> {
        return recordDao.getAllRecords()
    }

    override suspend fun insertRecord(record: AudioRecord) {
        return recordDao.insertRecord(record)
    }

    override suspend fun deleteRecord(id: Int) {
        return recordDao.deleteRecord(id)
    }
}