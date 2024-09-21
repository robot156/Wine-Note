package com.winenote.feature.setting

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.winenote.core.domain.usecase.record.ClearWineRecordUseCase
import com.winenote.core.domain.usecase.setting.GetThemeUseCase
import com.winenote.core.domain.usecase.setting.SetThemeUseCase
import com.winenote.core.model.ThemeConfig
import com.winenote.core.model.findTheme
import com.winenote.core.resource.R
import com.winenote.core.ui.base.BaseViewModel
import com.winenote.core.ui.state.UiAction
import com.winenote.core.ui.state.UiEvent
import com.winenote.core.ui.state.UiState
import com.winenote.core.ui.state.checkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingViewModel @Inject constructor(
    getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase,
    private val clearWineRecordUseCase: ClearWineRecordUseCase
) : BaseViewModel() {

    private val currentTheme = getThemeUseCase(Unit)
        .map { theme -> theme.findTheme() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ThemeConfig.FOLLOW_SYSTEM)

    private val _settingUiState = MutableStateFlow<SettingUiState>(SettingUiState.Setting())
    val settingUiState: StateFlow<SettingUiState> = _settingUiState

    init {
        viewModelScope.launch {
            currentTheme.collect { theme ->
                _settingUiState.update {
                    SettingUiState.Setting(currentThemeConfig = theme)
                }
            }
        }
    }

    fun onUiAction(uiAction: SettingUiAction) {
        when (uiAction) {
            is SettingUiAction.OnClickBack -> setUiEvent(SettingUiEvent.NavigateToBack)
            is SettingUiAction.OnClickBin -> setUiEvent(SettingUiEvent.NavigateToBin)
            is SettingUiAction.OnClickThemeSetting -> setCurrentTheme(uiAction.theme)
            is SettingUiAction.OnClickRecordClear -> clearWineRecord()
            is SettingUiAction.OnShowRecordClearDialog -> showRecordClearDialog(uiAction.isShow)
            is SettingUiAction.OnShowThemeDialog -> showThemeDialog(uiAction.isShow)
        }
    }

    private fun showRecordClearDialog(isShow: Boolean) {
        settingUiState.checkState<SettingUiState.Setting> {
            _settingUiState.update { copy(isShowRecordClearDialog = isShow) }
        }
    }

    private fun showThemeDialog(isShow: Boolean) {
        settingUiState.checkState<SettingUiState.Setting> {
            _settingUiState.update { copy(isShowThemeDialog = isShow) }
        }
    }

    private fun setCurrentTheme(theme: ThemeConfig) {
        viewModelScope.launch {
            setThemeUseCase(theme.theme)
            showThemeDialog(false)
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
        val currentThemeConfig: ThemeConfig = ThemeConfig.FOLLOW_SYSTEM,
        val isShowRecordClearDialog: Boolean = false,
        val isShowThemeDialog: Boolean = false
    ) : SettingUiState
}

sealed interface SettingUiAction : UiAction {
    data object OnClickBack : SettingUiAction
    data object OnClickBin : SettingUiAction
    data object OnClickRecordClear : SettingUiAction
    data class OnClickThemeSetting(val theme: ThemeConfig) : SettingUiAction
    data class OnShowRecordClearDialog(val isShow: Boolean) : SettingUiAction
    data class OnShowThemeDialog(val isShow: Boolean) : SettingUiAction
}

sealed interface SettingUiEvent : UiEvent {
    data object NavigateToBack : SettingUiEvent
    data object NavigateToBin : SettingUiEvent
    data class ShowToastMessage(@StringRes val messageRes: Int) : SettingUiEvent
}