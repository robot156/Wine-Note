package com.winenote.feature.setting

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.winenote.core.domain.usecase.record.ClearWineRecordUseCase
import com.winenote.core.resource.R
import com.winenote.core.ui.base.BaseViewModel
import com.winenote.core.ui.state.UiAction
import com.winenote.core.ui.state.UiEvent
import com.winenote.core.ui.state.UiState
import com.winenote.core.ui.state.checkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingViewModel @Inject constructor(
    private val clearWineRecordUseCase: ClearWineRecordUseCase
) : BaseViewModel() {

    private val _settingUiState = MutableStateFlow<SettingUiState>(SettingUiState.Setting())
    val settingUiState: StateFlow<SettingUiState> = _settingUiState

    fun onUiAction(uiAction: SettingUiAction) {
        when (uiAction) {
            is SettingUiAction.OnClickBack -> setUiEvent(SettingUiEvent.NavigateToBack)
            is SettingUiAction.OnClickBin -> setUiEvent(SettingUiEvent.NavigateToBin)
            is SettingUiAction.OnClickRecordClear -> clearWineRecord()
            is SettingUiAction.OnShowRecordClearDialog -> showRecordClearDialog(uiAction.isShow)
        }
    }

    private fun showRecordClearDialog(isShow: Boolean) {
        settingUiState.checkState<SettingUiState.Setting> {
            _settingUiState.update { copy(isShowRecordClearDialog = isShow) }
        }
    }

    private fun clearWineRecord() {
        viewModelScope.launch {
            clearWineRecordUseCase(Unit)
            showRecordClearDialog(false)
            setUiEvent(SettingUiEvent.ShowToastMessage(R.string.setting_clear_success))
        }
    }
}

sealed interface SettingUiState : UiState {
    data class Setting(
        val isShowRecordClearDialog: Boolean = false
    ) : SettingUiState
}

sealed interface SettingUiAction : UiAction {
    data object OnClickBack : SettingUiAction
    data object OnClickBin : SettingUiAction
    data object OnClickRecordClear : SettingUiAction
    data class OnShowRecordClearDialog(val isShow: Boolean) : SettingUiAction
}

sealed interface SettingUiEvent : UiEvent {
    data object NavigateToBack : SettingUiEvent
    data object NavigateToBin : SettingUiEvent
    data class ShowToastMessage(@StringRes val messageRes: Int) : SettingUiEvent
}