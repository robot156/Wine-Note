package com.winenote.core.domain.usecase.record.entity

import com.winenote.core.domain.Entity

data class WineRecordEntity(
    val id: String,
    val name: String,
    val color: String,
    val scentFeel: String,
    val region: String,
    val grapeVariety: String,
    val alcohol: Float,
    val scent: Float,
    val body: Float,
    val tannin: Float,
    val sugarContent: Float,
    val acidity: Float,
    val score: Float,
    val comment: String,
    val photoUrl: String?,
    val isDelete: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String?,
) : Entity