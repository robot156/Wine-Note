package com.winenote.core.domain.usecase.record

import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteWineRecordUseCase @Inject constructor(
    private val wineRecordRepository: WineRecordRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<DeleteWineRecordUseCase.Params, Unit>(ioDispatcher) {

    override fun execute(params: Params): Flow<Unit> = flow {
        emit(wineRecordRepository.deleteWineRecord(params.recordId))
    }

    data class Params(
        val recordId: String
    )
}