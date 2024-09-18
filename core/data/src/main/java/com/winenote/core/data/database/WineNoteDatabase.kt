package com.winenote.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.winenote.core.data.datasource.record.local.WineRecordDao
import com.winenote.core.data.model.WineRecord

@Database(
    entities = [WineRecord::class],
    version = 1,
    exportSchema = false
)
internal abstract class WineNoteDatabase : RoomDatabase() {

    abstract fun wineRecordDao(): WineRecordDao
}