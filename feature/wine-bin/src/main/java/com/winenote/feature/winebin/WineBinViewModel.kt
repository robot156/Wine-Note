package com.winenote.feature.winebin

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.winenote.core.domain.data
import com.winenote.core.domain.usecase.record.ClearBinWineRecordsUseCase
import com.winenote.core.domain.usecase.record.DeleteWineRecordUseCase
import com.winenote.core.domain.usecase.record.GetWineRecordForDeletedUseCase
import com.winenote.core.domain.usecase.record.GetWineRecordsUseCase
import com.winenote.core.domain.usecase.record.entity.WineRecordEntity
import com.winenote.core.model.asItem
import com.winenote.core.resource.R
import com.winenote.core.ui.base.BaseViewModel
import com.winenote.core.ui.state.UiAction
import com.winenote.core.ui.state.UiEvent
import com.winenote.core.ui.state.UiState
import com.winenote.core.ui.state.checkState
import com.winenote.core.ui.util.getDateTimeBetweenDay
import com.winenote.core.ui.util.getStringFormatZonedDateTime
import com.winenote.core.ui.util.getZonedDateTimeDefault
import com.winenote.feature.winebin.model.WineBinUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WineBinViewModel @Inject constructor(
    getWineRecordsUseCase: GetWineRecordsUseCase,
    private val getWineRecordForDeletedUseCase: GetWineRecordForDeletedUseCase,
    private val deleteWineRecordUseCase: DeleteWineRecordUseCase,
    private val clearBinWineRecordsUseCase: ClearBinWineRecordsUseCase,
) : BaseViewModel() {

    private val _wineBinUiState = MutableStateFlow<WineBinUiState>(WineBinUiState.WineBin())
    val wineBinUiState: StateFlow<WineBinUiState> = _wineBinUiState

    val wineRecords = loadDataSignal
        .mapLatest {
            val deletedRecords = getWineRecordForDeletedUseCase(Unit).data
                ?.map(WineRecordEntity::asItem)
                ?.filter { getStringFormatZonedDateTime(it.deletedAt).isBefore(getZonedDateTimeDefault().minusDays(1)) }
                ?: emptyList()

            deletedRecords.forEach {
                deleteWineRecordUseCase(DeleteWineRecordUseCase.Params(it.id)).first()
            }
        }
        .flatMapLatest {
            getWineRecordsUseCase(GetWineRecordsUseCase.Params(isDelete = true))
                .mapLatest { pagingData ->
                    pagingData.map { entity ->
                        val deleteDay = getDateTimeBetweenDay(
                            startDate = getZonedDateTimeDefault(),
                            endDate = getStringFormatZonedDateTime(entity.deletedAt)
                        )

                        WineBinUiModel.WineRecordItem(entity.asItem(), deleteDay)
                    }
                }
                .mapLatest { pagingData ->
                    pagingData.insertSeparators { before: WineBinUiModel?, after: WineBinUiModel? ->
                        if (before == null && after == null) {
                            setVisibleClearBinButton(false)
                            return@insertSeparators WineBinUiModel.EmptyItem
                        } else {
                            setVisibleClearBinButton(true)
                        }
                        null
                    }
                }
        }
        .cachedIn(viewModelScope)

    fun onUiAction(uiAction: WineBinUiAction) {
        when (uiAction) {
            is WineBinUiAction.OnClickBack -> setUiEvent(WineBinUiEvent.NavigateToBack)
            is WineBinUiAction.OnClickWineRecord -> setUiEvent(WineBinUiEvent.NavigateToWineDetail(uiAction.recordId))
            is WineBinUiAction.OnClickClearBin -> clearBinWineRecords()
            is WineBinUiAction.OnShowClearDialog -> showClearBinDialog(uiAction.isShow)
        }
    }

    private fun clearBinWineRecords() {
        viewModelScope.launch {
            clearBinWineRecordsUseCase(Unit)
            showClearBinDialog(false)
            setUiEvent(WineBinUiEvent.ShowToastMessage(R.string.bin_clear_success))
        }
    }

    private fun showClearBinDialog(isShow: Boolean) {
        wineBinUiState.checkState<WineBinUiState.WineBin> {
            _wineBinUiState.update { copy(isShowClearDialog = isShow) }
        }
    }

    private fun setVisibleClearBinButton(isVisible: Boolean) {
        wineBinUiState.checkState<WineBinUiState.WineBin> {
            if (this.isVisibleClearBinButton == isVisible) return@checkState
            _wineBinUiState.update { copy(isVisibleClearBinButton = isVisible) }
        }
    }
}

sealed interface WineBinUiState : UiState {
    data class WineBin(
        val isShowClearDialog: Boolean = false,
        val isVisibleClearBinButton: Boolean = true
    ) : WineBinUiState
}

sealed interface WineBinUiAction : UiAction {
    data object OnClickBack : WineBinUiAction
    data class OnClickWineRecord(val recordId: String) : WineBinUiAction
    data object OnClickClearBin : WineBinUiAction
    data class OnShowClearDialog(val isShow: Boolean) : WineBinUiAction
}

sealed interface WineBinUiEvent : UiEvent {
    data object NavigateToBack : WineBinUiEvent
    data class NavigateToWineDetail(val recordId: String) : WineBinUiEvent
    data class ShowToastMessage(@StringRes val messageRes: Int) : WineBinUiEvent
}