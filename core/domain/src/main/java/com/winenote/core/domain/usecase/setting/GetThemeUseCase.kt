package com.winenote.core.domain.usecase.setting

import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
): FlowUseCase<Unit, String>(ioDispatcher) {

    override fun execute(params: Unit): Flow<String> {
        return settingRepository.getCurrentTheme()
    }
}