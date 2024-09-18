package com.winenote.feature.winewrite.ui.step

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R
import com.winenote.core.ui.util.getDateTimeFormatString
import com.winenote.feature.winewrite.OnWineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiState
import com.winenote.feature.winewrite.ui.WineWriteTextField

@Composable
fun WineInfoScreen(
    modifier: Modifier = Modifier,
    uiState: WineWriteUiState.WineWrite = WineWriteUiState.WineWrite(),
    onUiAction: OnWineWriteUiAction = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(16.dp)
    ) {
        WineInfoTitle()

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.write_tasting_date),
            style = WineTheme.typography.bold14,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { onUiAction(WineWriteUiAction.OnShowDatePickerDialog(true)) },
            colors = ButtonDefaults.textButtonColors(),
            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onSurface),
            shape = RoundedCornerShape(6.dp),
        ) {
            Text(
                text = getDateTimeFormatString(uiState.record.updatedAt, stringResource(id = R.string.regex_date)),
                style = WineTheme.typography.regular12,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val contentModifier = Modifier.fillMaxWidth()

            WineWriteTextField(
                modifier = contentModifier,
                title = stringResource(id = R.string.write_wine_name),
                hintText = stringResource(id = R.string.write_wine_name_hint),
                value = uiState.record.name,
                onValueChange = { onUiAction(WineWriteUiAction.OnChangeWineName(it)) }
            )
            WineWriteTextField(
                modifier = contentModifier,
                title = stringResource(id = R.string.write_region),
                hintText = stringResource(id = R.string.write_region_hint),
                value = uiState.record.region,
                onValueChange = { onUiAction(WineWriteUiAction.OnChangeWineRegion(it)) }
            )
            WineWriteTextField(
                modifier = contentModifier,
                title = stringResource(id = R.string.write_alcohol),
                description = stringResource(id = R.string.write_alcohol_option),
                hintText = stringResource(id = R.string.write_alcohol_hint),
                value = uiState.record.alcohol.toString(),
                maxLength = 5,
                keyboardType = KeyboardType.Decimal,
                onValueChange = { onUiAction(WineWriteUiAction.OnChangeWineAlcohol(it)) }
            )
            WineWriteTextField(
                modifier = contentModifier,
                title = stringResource(id = R.string.write_grape_variety),
                hintText = stringResource(id = R.string.write_grape_variety_hint),
                value = uiState.record.grapeVariety,
                imeAction = ImeAction.Done,
                onValueChange = { onUiAction(WineWriteUiAction.OnChangeWineGrapeVariety(it)) }
            )
        }
    }
}

@Composable
private fun WineInfoTitle() {
    Text(
        text = stringResource(id = R.string.write_info_title),
        style = WineTheme.typography.bold18,
        color = MaterialTheme.colorScheme.onSurface,
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = stringResource(id = R.string.write_info_sub_title),
        style = WineTheme.typography.regular12,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@ThemePreviews
@Composable
private fun WineInfoScreenPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            WineInfoScreen()
        }
    }
}