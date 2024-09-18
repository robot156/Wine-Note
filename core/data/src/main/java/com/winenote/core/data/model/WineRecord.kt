package com.winenote.core.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity

@Entity(tableName = "WineRecord")
internal data class WineRecord(
    @PrimaryKey val id: String,
    @ColumnInfo val name: String,
    @ColumnInfo val color: String,
    @ColumnInfo val scentFeel: String,
    @ColumnInfo val region: String,
    @ColumnInfo val grapeVariety: String,
    @ColumnInfo val alcohol: Float,
    @ColumnInfo val scent: Float,
    @ColumnInfo val body: Float,
    @ColumnInfo val tannin: Float,
    @ColumnInfo val sugarContent: Float,
    @ColumnInfo val acidity: Float,
    @ColumnInfo val score: Float,
    @ColumnInfo val comment: String,
    @ColumnInfo val photoUrl: String?,
    @ColumnInfo val isDelete: Boolean,
    @ColumnInfo val createdAt: String,
    @ColumnInfo val updatedAt: String,
    @ColumnInfo val deletedAt: String?,
)

internal fun WineRecord.asEntity(): WineRecordEntity {
    return WineRecordEntity(
        id = id,
        name = name,
        color = color,
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

internal fun WineRecordEntity.asInternalModel(): WineRecord {
    return WineRecord(
        id = id,
        name = name,
        color = color,
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