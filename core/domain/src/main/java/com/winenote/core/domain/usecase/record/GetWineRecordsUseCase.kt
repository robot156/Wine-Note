package com.winenote.core.domain.usecase.record

import androidx.paging.PagingData
import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.FlowUseCase
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWineRecordsUseCase @Inject constructor(
    private val wineRecordRepository: WineRecordRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
): FlowUseCase<GetWineRecordsUseCase.Params, PagingData<WineRecordEntity>>(ioDispatcher) {

    override fun execute(params: Params): Flow<PagingData<WineRecordEntity>> {
        return wineRecordRepository.getWineRecords(params.isDelete)
    }

    data class Params(
        val isDelete: Boolean = false
    )
}