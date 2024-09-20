package com.winenote.feature.winedetail

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.winenote.core.domain.Result
import com.winenote.core.domain.asResult
import com.winenote.core.domain.usecase.record.DeleteWineRecordUseCase
import com.winenote.core.domain.usecase.record.GetWineRecordAtFlowUseCase
import com.winenote.core.domain.usecase.record.UpdateWineRecordUseCase
import com.winenote.core.model.WineRecord
import com.winenote.core.model.asEntity
import com.winenote.core.model.asItem
import com.winenote.core.ui.base.BaseViewModel
import com.winenote.core.ui.state.UiAction
import com.winenote.core.ui.state.UiEvent
import com.winenote.core.ui.state.UiState
import com.winenote.core.ui.state.checkState
import com.winenote.core.ui.util.getZonedDateTimeWithSyncZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WineDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getWineRecordUseCase: GetWineRecordAtFlowUseCase,
    private val updateWineRecordUseCase: UpdateWineRecordUseCase,
    private val deleteWineRecordUseCase: DeleteWineRecordUseCase,
) : BaseViewModel() {

    private val detailArgs = DetailArgs(savedStateHandle)

    private val wineRecordResult = loadDataSignal
        .flatMapLatest { getWineRecordUseCase(GetWineRecordAtFlowUseCase.Params(detailArgs.recordId)).asResult() }
        .stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)

    private val _wineDetailUiState = MutableStateFlow<WineDetailUiState>(WineDetailUiState.None)
    val wineDetailUiState: StateFlow<WineDetailUiState> = _wineDetailUiState

    init {
        viewModelScope.launch {
            wineRecordResult.collect { result ->
                _wineDetailUiState.update {
                    when (result) {
                        is Result.Success -> WineDetailUiState.WineDetail(result.data.asItem())
                        else -> WineDetailUiState.None
                    }
                }
            }
        }
    }

    fun onUiAction(uiAction: WineDetailUiAction) {
        when (uiAction) {
            is WineDetailUiAction.OnClickBack -> setUiEvent(WineDetailEvent.NavigateToBack)
            is WineDetailUiAction.OnClickUpdateRecord -> setWineRecord()
            is WineDetailUiAction.OnClickDeleteRecord -> deleteWineRecord()
            is WineDetailUiAction.OnClickShareRecord -> setWineRecordShare(uiAction.isShare)
            is WineDetailUiAction.OnShareRecord -> navigateToShareRecord(uiAction.bitmap)
            is WineDetailUiAction.OnShowEditDialog -> showEditDialog(uiAction.isShow)
            is WineDetailUiAction.OnShowPhotoDialog -> showPhotoDialog(uiAction.isShow)
        }
    }

    private fun setWineRecord() {
        viewModelScope.launch {
            wineDetailUiState.checkState<WineDetailUiState.WineDetail> {
                if (record.isDelete) {
                    updateWineRecordUseCase(UpdateWineRecordUseCase.Params(record.copy(deletedAt = null, isDelete = false).asEntity())).first()
                    setUiEvent(WineDetailEvent.NavigateToBack)
                } else {
                    setUiEvent(WineDetailEvent.NavigateToWineWrite(record.id))
                }
            }
        }
    }

    private fun deleteWineRecord() {
        viewModelScope.launch {
            wineDetailUiState.checkState<WineDetailUiState.WineDetail> {
                if (record.isDelete) {
                    deleteWineRecordUseCase(DeleteWineRecordUseCase.Params(record.id))
                } else {
                    updateWineRecordUseCase(UpdateWineRecordUseCase.Params(record.copy(deletedAt = getZonedDateTimeWithSyncZero().plusDays(15).toString(), isDelete = true).asEntity()))
                }.collect {
                    setUiEvent(WineDetailEvent.NavigateToBack)
                }
            }
        }
    }

    private fun setWineRecordShare(isShare: Boolean) {
        wineDetailUiState.checkState<WineDetailUiState.WineDetail> {
            _wineDetailUiState.update { copy(isShared = isShare) }
        }
    }

    private fun navigateToShareRecord(bitmap: Bitmap) {
        setWineRecordShare(false)
        setUiEvent(WineDetailEvent.NavigateToShareRecord(bitmap))
    }

    private fun showEditDialog(isShow: Boolean) {
        wineDetailUiState.checkState<WineDetailUiState.WineDetail> {
            _wineDetailUiState.update { copy(isShowEditDialog = isShow) }
        }
    }

    private fun showPhotoDialog(isShow: Boolean) {
        wineDetailUiState.checkState<WineDetailUiState.WineDetail> {
            _wineDetailUiState.update { copy(isShowPhotoDialog = isShow) }
        }
    }
}

sealed interface WineDetailUiState : UiState {
    data class WineDetail(
        val record: WineRecord,
        val isShowEditDialog: Boolean = false,
        val isShowPhotoDialog: Boolean = false,
        val isShared: Boolean = false
    ) : WineDetailUiState

    data object None : WineDetailUiState
}

sealed interface WineDetailUiAction : UiAction {
    data object OnClickBack : WineDetailUiAction
    data object OnClickDeleteRecord : WineDetailUiAction
    data object OnClickUpdateRecord : WineDetailUiAction
    data class OnClickShareRecord(val isShare: Boolean) : WineDetailUiAction
    data class OnShowEditDialog(val isShow: Boolean) : WineDetailUiAction
    data class OnShowPhotoDialog(val isShow: Boolean) : WineDetailUiAction
    data class OnShareRecord(val bitmap: Bitmap) : WineDetailUiAction
}

sealed interface WineDetailEvent : UiEvent {
    data object NavigateToBack : WineDetailEvent
    data class NavigateToWineWrite(val recordId: String) : WineDetailEvent
    data class NavigateToShareRecord(val bitmap: Bitmap) : WineDetailEvent
}