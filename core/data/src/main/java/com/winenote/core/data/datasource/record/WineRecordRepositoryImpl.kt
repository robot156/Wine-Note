package com.winenote.core.data.datasource.record

import androidx.paging.PagingData
import com.winenote.core.data.datasource.record.local.WineRecordLocalDataSource
import com.winenote.core.data.datasource.record.remote.WineRecordRemoteDataSource
import com.winenote.core.domain.usecase.record.WineRecordRepository
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class WineRecordRepositoryImpl @Inject constructor(
    private val localDataSource: WineRecordLocalDataSource,
    private val remoteDataSource: WineRecordRemoteDataSource,
) : WineRecordRepository {

    override fun getWineRecords(isDelete: Boolean): Flow<PagingData<WineRecordEntity>> {
        return localDataSource.getWineRecords(isDelete)
    }

    override suspend fun getWineRecord(recordId: String): WineRecordEntity? {
        return localDataSource.getWineRecord(recordId)
    }

    override suspend fun getWineRecordForDeleted(): List<WineRecordEntity> {
        return localDataSource.getWineRecordForDeleted()
    }

    override suspend fun insertWineRecord(wineRecord: WineRecordEntity) {
        val uploadImageUrl = wineRecord.photoUrl?.let { remoteDataSource.imageUpload(it) }
        return localDataSource.insertWineRecord(wineRecord.copy(photoUrl = uploadImageUrl))
    }

    override suspend fun updateWineRecord(wineRecord: WineRecordEntity) {
        val currentWineRecord = localDataSource.getWineRecord(wineRecord.id)
        val uploadImageUrl = if (currentWineRecord?.photoUrl != wineRecord.photoUrl && wineRecord.photoUrl != null) {
            remoteDataSource.imageUpload(wineRecord.photoUrl!!)
        } else {
            currentWineRecord?.photoUrl
        }

        return localDataSource.updateWineRecord(wineRecord.copy(photoUrl = uploadImageUrl))
    }

    override suspend fun deleteWineRecord(recordId: String) {
        return localDataSource.deleteWineRecord(recordId)
    }

    override suspend fun clearBinWineRecords() {
        return localDataSource.clearBinWineRecords()
    }

    override suspend fun clearWineRecord() {
        return localDataSource.clearWineRecord()
    }
}