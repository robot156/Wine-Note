package com.winenote.feature.winelist.model

import com.winenote.core.model.WineRecord

sealed class WineListUiModel {
    data class WineRecordItem(val record: WineRecord) : WineListUiModel()
    data class WineDateHeaderItem(val yearAndMonth: String) : WineListUiModel()
    data object EmptyItem : WineListUiModel()
}