package com.winenote.feature.winewrite.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.component.WineOutlineButton
import com.winenote.core.designsystem.component.WinePrimaryButton
import com.winenote.feature.winewrite.OnWineWriteUiAction
import com.winenote.core.resource.R
import com.winenote.feature.winewrite.WineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiState

@Composable
fun WineWriteBottomBar(
    modifier: Modifier = Modifier,
    uiState: WineWriteUiState.WineWrite,
    onUiAction: OnWineWriteUiAction = {}
) {
    Row(
        modifier = modifier.padding(16.dp)
    ) {
        if (uiState.currentIndex > 1) {
            WineOutlineButton(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.common_prev),
                onClick = { onUiAction(WineWriteUiAction.OnClickBack) }
            )

            Spacer(modifier = Modifier.width(14.dp))
        }

        WinePrimaryButton(
            modifier = Modifier.weight(1f),
            text = stringResource(id = if (uiState.currentIndex != uiState.totalIndex) R.string.common_next else R.string.common_save),
            onClick = { onUiAction(if (uiState.currentIndex != uiState.totalIndex) WineWriteUiAction.OnClickNext else WineWriteUiAction.OnClickSave) }
        )
    }
}