package com.winenote.core.domain.usecase.record

import androidx.paging.PagingData
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import kotlinx.coroutines.flow.Flow

interface WineRecordRepository {

    fun getWineRecords(isDelete: Boolean = false): Flow<PagingData<WineRecordEntity>>

    suspend fun getWineRecord(recordId: String): WineRecordEntity?

    suspend fun getWineRecordForDeleted(): List<WineRecordEntity>

    suspend fun insertWineRecord(wineRecord: WineRecordEntity)

    suspend fun updateWineRecord(wineRecord: WineRecordEntity)

    suspend fun deleteWineRecord(recordId: String)

    suspend fun clearBinWineRecords()

    suspend fun clearWineRecord()
}