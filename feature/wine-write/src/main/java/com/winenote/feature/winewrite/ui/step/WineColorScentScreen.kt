package com.winenote.feature.winewrite.ui.step

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.DessertWine
import com.winenote.core.designsystem.theme.RedWine
import com.winenote.core.designsystem.theme.RoseWine
import com.winenote.core.designsystem.theme.SparklingWine
import com.winenote.core.designsystem.theme.WhiteWine
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.model.WineColor
import com.winenote.core.model.WineRecord
import com.winenote.core.resource.R
import com.winenote.feature.winewrite.OnWineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiState
import com.winenote.feature.winewrite.ui.WineSlider
import com.winenote.feature.winewrite.ui.WineWriteTextField

@Composable
fun WineColorScentScreen(
    modifier: Modifier = Modifier,
    uiState: WineWriteUiState.WineWrite = WineWriteUiState.WineWrite(),
    onUiAction: OnWineWriteUiAction = {}
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
            text = stringResource(id = R.string.write_color_and_scent_title),
            style = WineTheme.typography.bold18,
            color = MaterialTheme.colorScheme.onSurface,
        )

        WineBottles(
            selectColor = uiState.record.color,
            onClick = { onUiAction(WineWriteUiAction.OnClickWineColor(it)) },
        )

        WineSlider(
            title = stringResource(id = R.string.write_scent),
            currentPosition = uiState.record.scent,
            valueStartText = stringResource(id = R.string.write_weak),
            valueEndText = stringResource(id = R.string.write_strong),
            onChangePosition = { onUiAction(WineWriteUiAction.OnChangeWineScent(it)) }
        )

        WineWriteTextField(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.write_scent_feel),
            value = uiState.record.scentFeel,
            hintText = stringResource(id = R.string.write_scent_feel_hint),
            onValueChange = { onUiAction(WineWriteUiAction.OnChangeWineScentFeel(it)) }
        )
    }
}

@Composable
private fun WineBottles(
    selectColor: WineColor,
    onClick: (WineColor) -> Unit = {},
) {
    Column {

        Text(
            text = stringResource(id = R.string.write_color),
            style = WineTheme.typography.bold14,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            val modifier = Modifier.weight(1f)

            WineBottle(
                modifier = modifier,
                color = SparklingWine,
                colorName = stringResource(id = R.string.write_color_sparkling),
                isChecked = WineColor.Sparkling == selectColor,
                onClick = { onClick(WineColor.Sparkling) }
            )
            WineBottle(
                modifier = modifier,
                color = WhiteWine,
                colorName = stringResource(id = R.string.write_color_white),
                isChecked = WineColor.White == selectColor,
                onClick = { onClick(WineColor.White) }
            )
            WineBottle(
                modifier = modifier,
                color = RoseWine,
                colorName = stringResource(id = R.string.write_color_rose),
                isChecked = WineColor.Rose == selectColor,
                onClick = { onClick(WineColor.Rose) }
            )
            WineBottle(
                modifier = modifier,
                color = RedWine,
                colorName = stringResource(id = R.string.write_color_red),
                isChecked = WineColor.Red == selectColor,
                onClick = { onClick(WineColor.Red) }
            )
            WineBottle(
                modifier = modifier,
                color = DessertWine,
                colorName = stringResource(id = R.string.write_color_dessert),
                isChecked = WineColor.Dessert == selectColor,
                onClick = { onClick(WineColor.Dessert) }
            )
        }
    }
}

@Composable
private fun WineBottle(
    modifier: Modifier = Modifier,
    color: Color,
    colorName: String,
    isChecked: Boolean,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(width = 24.dp, height = 74.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_wine_bottle),
            contentDescription = "wine",
            tint = color
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = colorName,
            style = WineTheme.typography.regular12,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (isChecked) {
            Spacer(modifier = Modifier.height(4.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
@ThemePreviews
private fun WineColorScentScreenPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            WineColorScentScreen(
                uiState = WineWriteUiState.WineWrite(
                    record = WineRecord().copy(scent = 1f)
                )
            )
        }
    }
}