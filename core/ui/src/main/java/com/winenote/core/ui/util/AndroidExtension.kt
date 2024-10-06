package com.winenote.core.ui.util

import android.content.Context
import android.graphics.Bitmap.CompressFormat.PNG
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import com.winenote.core.designsystem.theme.DessertWine
import com.winenote.core.designsystem.theme.RedWine
import com.winenote.core.designsystem.theme.RoseWine
import com.winenote.core.designsystem.theme.SparklingWine
import com.winenote.core.designsystem.theme.WhiteWine
import com.winenote.core.model.WineColor
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

fun Context.externalShareForBitmap(bitmap: ImageBitmap) {
    try {
        val file = File(bitmap.saveToDisk(this))
        val uri = FileProvider.getUriForFile(this, "$packageName.provider", file)

        ShareCompat.IntentBuilder(this)
            .setStream(uri)
            .setType("image/png")
            .startChooser()
    } catch (e: Exception) {
        Timber.e("[externalShareFoBitmap] message: ${e.message}")
    }
}

fun getWineBottleColor(color: WineColor) : Color {
    return when (color) {
        WineColor.Red -> RedWine
        WineColor.Rose -> RoseWine
        WineColor.White -> WhiteWine
        WineColor.Dessert -> DessertWine
        WineColor.Sparkling -> SparklingWine
    }
}

private fun ImageBitmap.saveToDisk(context: Context): String {
    val fileName = "shared_image_${System.currentTimeMillis()}.png"
    val cachePath = File(context.cacheDir, "images").also { it.mkdirs() }
    val file = File(cachePath, fileName)
    val outputStream = FileOutputStream(file)

    asAndroidBitmap().compress(PNG, 100, outputStream)
    outputStream.flush()
    outputStream.close()

    return file.absolutePath
}