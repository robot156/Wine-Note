package com.winenote.core.model

import androidx.compose.runtime.Stable
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import java.time.ZonedDateTime

@Stable
data class WineRecord(
    val id: String = "",
    val name: String = "",
    val color: WineColor = WineColor.Red,
    val scentFeel: String = "",
    val region: String = "",
    val grapeVariety: String = "",
    val alcohol: Float = 3f,
    val scent: Float = 3f,
    val body: Float = 3f,
    val tannin: Float = 3f,
    val sugarContent: Float = 3f,
    val acidity: Float = 3f,
    val score: Float = 3f,
    val comment: String = "",
    val photoUrl: String? = null,
    val isDelete: Boolean = false,
    val createdAt: String = ZonedDateTime.now().toString(),
    val updatedAt: String = ZonedDateTime.now().toString(),
    val deletedAt: String? = null
)

fun WineRecordEntity.asItem(): WineRecord {
    return WineRecord(
        id = id,
        name = name,
        color = getWineColor(color),
        scentFeel = scentFeel,
        region = region,
        grapeVariety = grapeVariety,
        alcohol = alcohol,
        scent = scent,
        body = body,
        tannin = tannin,
        sugarContent = sugarContent,
        acidity = acidity,
        score = score,
        comment = comment,
        photoUrl = photoUrl,
        isDelete = isDelete,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt
    )
}

fun WineRecord.asEntity(): WineRecordEntity {
    return WineRecordEntity(
        id = id,
        name = name,
        color = color.name,
        scentFeel = scentFeel,
        region = region,
        grapeVariety = grapeVariety,
        alcohol = alcohol,
        scent = scent,
        body = body,
        tannin = tannin,
        sugarContent = sugarContent,
        acidity = acidity,
        score = score,
        comment = comment,
        photoUrl = photoUrl,
        isDelete = isDelete,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt
    )
}

/*
{
  "id" : "dafb175d-52cd-4b22-a33d-9f73b3d25c98", // UUID
  "name" : "와인이름",
  "color" : "Red",
  "scentFeel" : "열대 과일 향이 느껴짐",
  "region" : "캘리포니아",
  "grapeVariety" : "소비뇽",
  "alcohol" : 15.5,
  "scent" : 3.5,
  "body" : 3.5,
  "tannin" : 3.5,
  "sugarContent" : 3.5,
  "acidity" : 3.5,
  "score" : 3.5,
  "comment" : "선물 받은 와인. 기념일에 먹기 좋았다.",
  "isDelete" : false,
  "createdAt" : "2024-05-24",
  "updatedAt" : "2024-05-24"
}
*/