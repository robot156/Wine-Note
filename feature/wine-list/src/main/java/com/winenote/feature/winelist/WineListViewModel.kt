package com.winenote.feature.winelist

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.winenote.core.domain.usecase.record.GetWineRecordsUseCase
import com.winenote.core.model.asItem
import com.winenote.core.ui.base.BaseViewModel
import com.winenote.core.ui.state.UiAction
import com.winenote.core.ui.state.UiEvent
import com.winenote.core.ui.util.getDateTimeFormatString
import com.winenote.core.ui.util.isSameMonth
import com.winenote.feature.winelist.model.WineListUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
internal class WineListViewModel @Inject constructor(
    getWineRecordsUseCase: GetWineRecordsUseCase
) : BaseViewModel() {

    val wineRecords = getWineRecordsUseCase(GetWineRecordsUseCase.Params())
        .mapLatest { pagingData -> pagingData.map { entity -> WineListUiModel.WineRecordItem(entity.asItem()) } }
        .map { pagingData ->
            pagingData.insertSeparators { before: WineListUiModel?, after: WineListUiModel? ->
                if (before == null && after == null) {
                    return@insertSeparators WineListUiModel.EmptyItem
                } else if (before != null && after != null) {
                    val beforeRecord = (before as WineListUiModel.WineRecordItem).record
                    val afterRecord = (after as WineListUiModel.WineRecordItem).record

                    if (!isSameMonth(beforeRecord.updatedAt, afterRecord.updatedAt)) {
                        return@insertSeparators WineListUiModel.WineDateHeaderItem(getDateTimeFormatString(afterRecord.updatedAt, "yy.MM"))
                    }
                }

                null
            }
        }
        .cachedIn(viewModelScope)

    fun onUiAction(uiAction: WineListUiAction) {
        when (uiAction) {
            is WineListUiAction.OnClickSetting -> setUiEvent(WineListUiEvent.NavigateToSetting)
            is WineListUiAction.OnClickWrite -> setUiEvent(WineListUiEvent.NavigateToWrite)
            is WineListUiAction.OnClickWineRecord -> setUiEvent(WineListUiEvent.NavigateToWineDetail(uiAction.recordId))
        }
    }
}

sealed interface WineListUiAction : UiAction {
    data object OnClickSetting : WineListUiAction
    data object OnClickWrite : WineListUiAction
    data class OnClickWineRecord(val recordId: String) : WineListUiAction
}

sealed interface WineListUiEvent : UiEvent {
    data object NavigateToSetting : WineListUiEvent
    data object NavigateToWrite : WineListUiEvent
    data class NavigateToWineDetail(val recordId: String) : WineListUiEvent
}