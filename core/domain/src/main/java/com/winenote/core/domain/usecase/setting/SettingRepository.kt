package com.winenote.core.domain.usecase.setting

import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    fun getCurrentTheme(): Flow<String>
    suspend fun setCurrentTheme(theme: String)
}