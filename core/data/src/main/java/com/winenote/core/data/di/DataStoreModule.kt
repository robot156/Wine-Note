package com.winenote.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.winenote.core.data.datastore.PreferenceStorage
import com.winenote.core.data.datastore.PreferenceStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object DataStoreModule {

    private val Context.preference: DataStore<Preferences> by preferencesDataStore(
        name = PreferenceStorageImpl.PREFS_WINE_NOTE_DATA_STORE
    )

    @Singleton
    @Provides
    fun providePreferenceStorageModule(
        @ApplicationContext context: Context,
    ): PreferenceStorage {
        return PreferenceStorageImpl(
            preference = context.preference,
        )
    }
}