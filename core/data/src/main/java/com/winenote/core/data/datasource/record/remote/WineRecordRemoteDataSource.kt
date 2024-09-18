package com.winenote.core.data.datasource.record.remote

internal interface WineRecordRemoteDataSource {

    suspend fun imageUpload(imagePath: String): String
}