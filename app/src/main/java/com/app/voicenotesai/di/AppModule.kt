package com.app.voicenotesai.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.app.voicenotesai.data.local.database.RecordDatabase
import com.app.voicenotesai.data.repository.RecordRepositoryImpl
import com.app.voicenotesai.domain.repository.RecordRepository
import com.app.voicenotesai.voice_to_text.VoiceToTextParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideRecordDatabase(
        @ApplicationContext context: Context
    ): RecordDatabase =
        Room.databaseBuilder(
            context,
            RecordDatabase::class.java,
            RecordDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideRecordRepository(
        database: RecordDatabase
    ) : RecordRepository = RecordRepositoryImpl(recordDao = database.recordDao())

}