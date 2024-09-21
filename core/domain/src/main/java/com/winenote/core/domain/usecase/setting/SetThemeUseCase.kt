package com.winenote.core.domain.usecase.setting

import com.winenote.core.domain.di.IoDispatcher
import com.winenote.core.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SetThemeUseCase @Inject constructor(
    private val settingRepository: SettingRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<String, Unit>(ioDispatcher) {

    override suspend fun execute(params: String) {
        return settingRepository.setCurrentTheme(params)
    }
}