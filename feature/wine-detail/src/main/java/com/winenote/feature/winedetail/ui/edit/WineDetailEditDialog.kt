package com.winenote.feature.winedetail.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.component.WineBottomSheetDialog
import com.winenote.core.designsystem.component.WineOutlineButton
import com.winenote.core.designsystem.component.WinePrimaryButton
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R
import com.winenote.feature.winedetail.OnWineDetailUiAction
import com.winenote.feature.winedetail.WineDetailUiAction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WineDetailEditDialog(
    modifier: Modifier = Modifier,
    isDeleted: Boolean = false,
    onUiAction: OnWineDetailUiAction = {}
) {
    val sheetState: SheetState = rememberModalBottomSheetState(true)
    val coroutineScope = rememberCoroutineScope()

    WineBottomSheetDialog(
        modifier = modifier.fillMaxWidth(),
        onDismiss = { onUiAction(WineDetailUiAction.OnShowEditDialog(false)) }
    ) {
        WineDetailEditScreen(
            isDeleted = isDeleted,
            onUiAction = { uiAction ->
                coroutineScope
                    .launch {
                        sheetState.hide()
                        onUiAction(WineDetailUiAction.OnShowEditDialog(false))
                        delay(200)
                    }
                    .invokeOnCompletion { onUiAction(uiAction) }
            }
        )
    }
}

@Composable
private fun WineDetailEditScreen(
    isDeleted: Boolean = false,
    onUiAction: OnWineDetailUiAction = {}
) {
    val title = stringResource(id = if (isDeleted) R.string.detail_delete_title else R.string.detail_edit_title)
    val description = stringResource(id = if (isDeleted) R.string.detail_delete_description else R.string.detail_edit_description)
    val btnEditText = stringResource(id = if (isDeleted) R.string.common_restore else R.string.common_edit)

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = WineTheme.typography.regular18,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = description,
            style = WineTheme.typography.regular14,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        WineOutlineButton(
            modifier = Modifier.fillMaxWidth(),
            text = btnEditText,
            onClick = { onUiAction(WineDetailUiAction.OnClickUpdateRecord) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        WinePrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.common_delete),
            onClick = { onUiAction(WineDetailUiAction.OnClickDeleteRecord) }
        )
    }
}

@ThemePreviews
@Composable
private fun WineDetailEditScreenPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            WineDetailEditScreen(
                isDeleted = false,
                onUiAction = {}
            )
        }
    }
}