package com.winenote.core.data.datasource.setting

import com.winenote.core.data.datasource.setting.local.SettingLocalDataSource
import com.winenote.core.domain.usecase.setting.SettingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingRepositoryImpl @Inject constructor(
    private val localDataSource: SettingLocalDataSource
) : SettingRepository {

    override fun getCurrentTheme(): Flow<String> {
        return localDataSource.getCurrentTheme()
    }

    override suspend fun setCurrentTheme(theme: String) {
        return localDataSource.setCurrentTheme(theme)
    }
}