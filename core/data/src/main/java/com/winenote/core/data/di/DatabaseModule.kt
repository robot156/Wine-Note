package com.winenote.core.data.di

import android.content.Context
import androidx.room.Room
import com.winenote.core.data.database.WineNoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideWineNoteDatabase(
        @ApplicationContext context: Context
    ): WineNoteDatabase {
        return Room
            .databaseBuilder(context, WineNoteDatabase::class.java, "wine-note-db")
            .enableMultiInstanceInvalidation()
            .build()
    }

    @Provides
    fun provideWineNoteDao(dataBase: WineNoteDatabase) = dataBase.wineRecordDao()
}