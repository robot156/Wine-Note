package com.winenote.core.domain.usecase.record

import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.FlowUseCase
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWineRecordAtFlowUseCase @Inject constructor(
    private val wineRecordRepository: WineRecordRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<GetWineRecordAtFlowUseCase.Params, WineRecordEntity>(ioDispatcher){

    override fun execute(params: Params): Flow<WineRecordEntity> = flow {
        emit(wineRecordRepository.getWineRecord(params.recordId)!!)
    }

    data class Params(
        val recordId: String
    )
}