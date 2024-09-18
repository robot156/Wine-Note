package com.winenote.core.data.datasource.record.remote

import com.winenote.core.data.util.CompressorUtil
import com.winenote.core.data.util.FileUtil
import com.winenote.core.data.util.FirebaseStorageUtil
import kotlinx.coroutines.withTimeout
import timber.log.Timber
import javax.inject.Inject

internal class WineRecordRemoteDataSourceImpl @Inject constructor(
    private val fileUtil: FileUtil,
    private val compressorUtil: CompressorUtil,
    private val firebaseStorageUtil: FirebaseStorageUtil
) : WineRecordRemoteDataSource {

    override suspend fun imageUpload(imagePath: String): String {
        return try {
            withTimeout(10_000L) {
                fileUtil.from(imagePath).let { uploadFile ->
                    compressorUtil.compressFile(uploadFile).let { compressorFile ->
                        firebaseStorageUtil.uploadImageToFirebase(compressorFile)
                    }
                }
            }
        } catch (exception: Exception) {
            Timber.e("exception ${exception.message} ")
            throw Exception("image upload fail exception!")
        }
    }
}