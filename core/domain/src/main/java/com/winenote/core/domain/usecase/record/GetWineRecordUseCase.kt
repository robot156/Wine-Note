package com.winenote.core.domain.usecase.record

import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.UseCase
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetWineRecordUseCase @Inject constructor(
    private val wineRecordRepository: WineRecordRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<GetWineRecordUseCase.Params, WineRecordEntity?>(ioDispatcher) {

    override suspend fun execute(params: Params): WineRecordEntity? {
        return wineRecordRepository.getWineRecord(params.recordId)
    }

    data class Params(
        val recordId: String
    )
}