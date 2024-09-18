package com.winenote.core.data.datasource.record.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.winenote.core.data.model.WineRecord

@Dao
internal interface WineRecordDao {

    @Query("SELECT * FROM WineRecord WHERE isDelete = 0 ORDER BY updatedAt desc")
    fun getWineRecords(): PagingSource<Int, WineRecord>

    @Query("SELECT * FROM WineRecord WHERE isDelete = 1 ORDER BY deletedAt desc")
    fun getWineRecordsForDeleted(): PagingSource<Int, WineRecord>

    @Query("SELECT * FROM WineRecord WHERE id is NULL OR id = :recordId")
    suspend fun getWineRecord(recordId: String): WineRecord?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWineRecord(wineRecord: WineRecord)

    @Update
    suspend fun updateWineRecord(wineRecord: WineRecord)

    @Query("DELETE FROM WineRecord WHERE id is NULL OR id = :recordId")
    suspend fun deleteWineRecord(recordId: String)

    @Query("DELETE FROM WineRecord WHERE isDelete")
    suspend fun clearBinWineRecords()

    @Query("DELETE FROM WineRecord")
    suspend fun clearWineRecord()
}