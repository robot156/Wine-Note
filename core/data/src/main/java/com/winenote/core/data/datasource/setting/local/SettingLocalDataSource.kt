package com.winenote.core.data.datasource.setting.local

import kotlinx.coroutines.flow.Flow

internal interface SettingLocalDataSource {

    fun getCurrentTheme(): Flow<String>
    suspend fun setCurrentTheme(theme : String)
}