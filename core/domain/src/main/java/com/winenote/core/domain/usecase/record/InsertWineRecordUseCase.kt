package com.winenote.core.domain.usecase.record

import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.FlowUseCase
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertWineRecordUseCase @Inject constructor(
    private val wineRecordRepository: WineRecordRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<InsertWineRecordUseCase.Params, Unit>(ioDispatcher) {

    override fun execute(params: Params) = flow {
        emit(wineRecordRepository.insertWineRecord(params.wineRecord))
    }

    data class Params(
        val wineRecord: WineRecordEntity
    )
}