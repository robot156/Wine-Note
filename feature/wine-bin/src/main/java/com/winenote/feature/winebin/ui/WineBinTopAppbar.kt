package com.winenote.feature.winebin.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.winenote.core.designsystem.component.WineTopAppbar
import com.winenote.core.resource.R
import com.winenote.feature.winebin.OnWineBinUiAction
import com.winenote.feature.winebin.WineBinUiAction

@Composable
fun WineBinTopAppbar(
    modifier: Modifier = Modifier,
    onUiAction: OnWineBinUiAction = {}
) {
    WineTopAppbar(
        modifier = modifier,
        title = stringResource(id = R.string.bin_title),
        onClickNavigate = { onUiAction(WineBinUiAction.OnClickBack) },
    )
}