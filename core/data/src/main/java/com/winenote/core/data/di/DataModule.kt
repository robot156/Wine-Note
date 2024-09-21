package com.winenote.core.data.di

import com.winenote.core.data.datasource.record.WineRecordRepositoryImpl
import com.winenote.core.data.datasource.record.local.WineRecordLocalDataSource
import com.winenote.core.data.datasource.record.local.WineRecordLocalDataSourceImpl
import com.winenote.core.data.datasource.record.remote.WineRecordRemoteDataSource
import com.winenote.core.data.datasource.record.remote.WineRecordRemoteDataSourceImpl
import com.winenote.core.data.datasource.setting.SettingRepositoryImpl
import com.winenote.core.data.datasource.setting.local.SettingLocalDataSource
import com.winenote.core.data.datasource.setting.local.SettingLocalDataSourceImpl
import com.winenote.core.domain.usecase.record.WineRecordRepository
import com.winenote.core.domain.usecase.setting.SettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    // ====================== Record ======================
    @Singleton
    @Binds
    abstract fun bindWineRecordRepository(userRepositoryImpl: WineRecordRepositoryImpl): WineRecordRepository

    @Singleton
    @Binds
    abstract fun bindWineRecordLocalDataSource(wineRecordLocalDataSourceImpl: WineRecordLocalDataSourceImpl): WineRecordLocalDataSource

    @Singleton
    @Binds
    abstract fun bindWineRecordRemoteDataSource(wineRecordRemoteDataSourceImpl: WineRecordRemoteDataSourceImpl): WineRecordRemoteDataSource

    // ====================== Setting ======================
    @Singleton
    @Binds
    abstract fun bindSettingRepository(settingRepositoryImpl: SettingRepositoryImpl): SettingRepository

    @Singleton
    @Binds
    abstract fun bindSettingLocalDataSource(settingLocalDataSourceImpl: SettingLocalDataSourceImpl): SettingLocalDataSource

}