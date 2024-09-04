package com.app.voicenotesai.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.voicenotesai.data.local.dao.RecordDao
import com.app.voicenotesai.data.local.entities.AudioRecord


@Database(entities = [AudioRecord::class], version = 1, exportSchema = false)
abstract class RecordDatabase : RoomDatabase() {

    abstract fun recordDao(): RecordDao

    companion object {
        const val DATABASE_NAME = "record_database"
    }
}