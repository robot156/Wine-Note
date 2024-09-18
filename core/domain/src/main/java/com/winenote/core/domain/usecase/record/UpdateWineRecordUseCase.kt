package com.winenote.core.domain.usecase.record

import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.FlowUseCase
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateWineRecordUseCase @Inject constructor(
    private val wineRecordRepository: WineRecordRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<UpdateWineRecordUseCase.Params, Unit>(ioDispatcher) {

    override fun execute(params: Params) = flow {
        emit(wineRecordRepository.updateWineRecord(params.wineRecord))
    }

    data class Params(
        val wineRecord: WineRecordEntity
    )
}