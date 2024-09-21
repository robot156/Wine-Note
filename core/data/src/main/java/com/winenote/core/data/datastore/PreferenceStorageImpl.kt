package com.winenote.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PreferenceStorageImpl @Inject constructor(
    private val preference: DataStore<Preferences>,
) : PreferenceStorage {

    override val getCurrentTheme: Flow<String> = preference.data
        .map { it[KEY_CURRENT_THEME] ?: "" }

    override suspend fun setCurrentTheme(theme: String) {
        preference.edit { it[KEY_CURRENT_THEME] = theme }
    }

    companion object {
        const val PREFS_WINE_NOTE_DATA_STORE = "prefsWineNoteDataStore"

        private val KEY_CURRENT_THEME = stringPreferencesKey("pref_current_theme")
    }
}