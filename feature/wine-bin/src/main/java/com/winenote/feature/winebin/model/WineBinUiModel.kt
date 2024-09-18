package com.winenote.feature.winebin.model

import com.winenote.core.model.WineRecord

sealed class WineBinUiModel {
    data class WineRecordItem(val record: WineRecord, val deleteDay: Int) : WineBinUiModel()
    data object EmptyItem : WineBinUiModel()
}