package com.winenote.feature.winewrite.ui.step

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R
import com.winenote.feature.winewrite.OnWineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiState
import com.winenote.feature.winewrite.ui.WineSlider

@Composable
fun WineTasteScreen(
    modifier: Modifier = Modifier,
    uiState: WineWriteUiState.WineWrite,
    onUiAction: OnWineWriteUiAction
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text(
            text = stringResource(id = R.string.write_taste_title),
            style = WineTheme.typography.bold18,
            color = MaterialTheme.colorScheme.onSurface,
        )

        WineSlider(
            title = stringResource(id = R.string.write_body),
            currentPosition = uiState.record.body,
            valueStartText = stringResource(id = R.string.write_light),
            valueEndText = stringResource(id = R.string.write_heavy),
            onChangePosition = { onUiAction(WineWriteUiAction.OnChangeWineBody(it)) }
        )

        WineSlider(
            title = stringResource(id = R.string.write_tannin),
            currentPosition = uiState.record.tannin,
            valueStartText = stringResource(id = R.string.write_little),
            valueEndText = stringResource(id = R.string.write_many),
            onChangePosition = { onUiAction(WineWriteUiAction.OnChangeWineTannin(it)) }
        )

        WineSlider(
            title = stringResource(id = R.string.write_sugar_content),
            currentPosition = uiState.record.sugarContent,
            valueStartText = stringResource(id = R.string.write_low),
            valueEndText = stringResource(id = R.string.write_high),
            onChangePosition = { onUiAction(WineWriteUiAction.OnChangeWineSugarContent(it)) }
        )

        WineSlider(
            title = stringResource(id = R.string.write_acidity),
            currentPosition = uiState.record.acidity,
            valueStartText = stringResource(id = R.string.write_low),
            valueEndText = stringResource(id = R.string.write_high),
            onChangePosition = { onUiAction(WineWriteUiAction.OnChangeWineAcidity(it)) }
        )
    }
}

@Composable
@ThemePreviews
private fun WineTasteScreenPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            WineTasteScreen(
                uiState = WineWriteUiState.WineWrite(),
                onUiAction = {}
            )
        }
    }
}
