package com.winenote.core.data.datasource.setting.local

import com.winenote.core.data.datastore.PreferenceStorage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingLocalDataSourceImpl @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : SettingLocalDataSource {

    override fun getCurrentTheme(): Flow<String> {
        return preferenceStorage.getCurrentTheme
    }

    override suspend fun setCurrentTheme(theme: String) {
        preferenceStorage.setCurrentTheme(theme)
    }
}