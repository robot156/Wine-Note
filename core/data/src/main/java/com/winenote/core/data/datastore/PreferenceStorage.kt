package com.winenote.core.data.datastore

import kotlinx.coroutines.flow.Flow

internal interface PreferenceStorage {

    val getCurrentTheme: Flow<String>
    suspend fun setCurrentTheme(theme : String)
}