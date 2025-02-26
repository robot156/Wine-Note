package com.winenote.core.data.di

import android.content.Context
import com.google.firebase.storage.FirebaseStorage
import com.winenote.core.data.util.CompressorUtil
import com.winenote.core.data.util.FileUtil
import com.winenote.core.data.util.FirebaseStorageUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object UtilModule {

    @Singleton
    @Provides
    fun provideCompressorUtil(
        @ApplicationContext context: Context
    ): CompressorUtil = CompressorUtil(context)

    @Singleton
    @Provides
    fun provideFileUtil(
        @ApplicationContext context: Context
    ): FileUtil = FileUtil(context)

    @Singleton
    @Provides
    fun provideFirebaseStorageUtil(): FirebaseStorageUtil = FirebaseStorageUtil(FirebaseStorage.getInstance())
}