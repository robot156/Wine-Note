package com.winenote.core.data.datasource.record.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.winenote.core.data.model.WineRecord
import com.winenote.core.data.model.asEntity
import com.winenote.core.data.model.asInternalModel
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class WineRecordLocalDataSourceImpl @Inject constructor(
    private val wineRecordDao: WineRecordDao
) : WineRecordLocalDataSource {

    override fun getWineRecords(isDelete: Boolean): Flow<PagingData<WineRecordEntity>> = Pager(
        config = PagingConfig(pageSize = 50, enablePlaceholders = false),
        pagingSourceFactory = { if (isDelete) wineRecordDao.getWineRecordsForDeletedAtFlow() else wineRecordDao.getWineRecords() }
    ).flow.map { pagingData -> pagingData.map(WineRecord::asEntity) }

    override suspend fun getWineRecord(recordId: String): WineRecordEntity? {
        return wineRecordDao.getWineRecordForId(recordId)?.asEntity()
    }

    override suspend fun getWineRecordForDeleted(): List<WineRecordEntity> {
        return wineRecordDao.getWineRecordsForDeleted().map(WineRecord::asEntity)
    }

    override suspend fun insertWineRecord(wineRecord: WineRecordEntity) {
        return wineRecordDao.insertWineRecord(wineRecord.asInternalModel())
    }

    override suspend fun updateWineRecord(wineRecord: WineRecordEntity) {
        return wineRecordDao.updateWineRecord(wineRecord.asInternalModel())
    }

    override suspend fun deleteWineRecord(recordId: String) {
        return wineRecordDao.deleteWineRecord(recordId)
    }

    override suspend fun clearBinWineRecords() {
        return wineRecordDao.clearBinWineRecords()
    }

    override suspend fun clearWineRecord() {
        return wineRecordDao.clearWineRecord()
    }
}