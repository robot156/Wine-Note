package com.winenote.feature.winewrite.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.winenote.core.designsystem.component.WineTopAppbar
import com.winenote.feature.winewrite.OnWineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiState

@Composable
fun WineWriteTopAppbar(
    modifier: Modifier = Modifier,
    uiState: WineWriteUiState.WineWrite,
    onUiAction: OnWineWriteUiAction = {}
) {
    WineTopAppbar(
        modifier = modifier,
        title = "${uiState.currentIndex} / ${uiState.totalIndex}",
        onClickNavigate = { onUiAction(WineWriteUiAction.OnClickBack) }
    )
}