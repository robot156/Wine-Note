package com.winenote.feature.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.winenote.core.ui.base.BaseViewModel
import com.winenote.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)

    init {
        viewModelScope.launch {
            delay(700)
            uiState.value = SplashUiState.Ready
        }
    }
}

internal sealed interface SplashUiState : UiState {
    data object Loading : SplashUiState
    data object Ready : SplashUiState
}