package com.winenote.core.domain.usecase.record

import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.UseCase
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetWineRecordForDeletedUseCase @Inject constructor(
    private val wineRecordRepository: WineRecordRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, List<WineRecordEntity>>(ioDispatcher) {

    override suspend fun execute(params: Unit): List<WineRecordEntity> {
        return wineRecordRepository.getWineRecordForDeleted()
    }
}
