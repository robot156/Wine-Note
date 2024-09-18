package com.winenote.core.domain.usecase.record

import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ClearWineRecordUseCase @Inject constructor(
    private val wineRecordRepository: WineRecordRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, Unit>(ioDispatcher) {

    override suspend fun execute(params: Unit) {
        return wineRecordRepository.clearWineRecord()
    }
}