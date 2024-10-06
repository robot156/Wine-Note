package com.winenote.feature.winedetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.model.WineRecord
import com.winenote.core.resource.R
import com.winenote.core.ui.util.getWineBottleColor

@Composable
fun WineDetailTasteRanges(
    modifier: Modifier = Modifier,
    wineRecord: WineRecord,
) {
    val wineColor = getWineBottleColor(wineRecord.color)

    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.detail_taste),
            style = WineTheme.typography.bold14,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        TasteRange(
            wineColor = wineColor,
            tasteText = stringResource(id = R.string.write_body),
            fillValue = wineRecord.body.toInt()
        )

        Spacer(modifier = Modifier.height(26.dp))

        TasteRange(
            wineColor = wineColor,
            tasteText = stringResource(id = R.string.write_tannin),
            fillValue = wineRecord.tannin.toInt()
        )
        Spacer(modifier = Modifier.height(26.dp))

        TasteRange(
            wineColor = wineColor,
            tasteText = stringResource(id = R.string.write_sugar_content),
            fillValue = wineRecord.sugarContent.toInt()
        )

        Spacer(modifier = Modifier.height(26.dp))

        TasteRange(
            wineColor = wineColor,
            tasteText = stringResource(id = R.string.write_acidity),
            fillValue = wineRecord.acidity.toInt()
        )
    }
}

@Composable
@ThemePreviews
private fun WineDetailTasteRangesPreview() {
    WineNoteTheme {
        WineDetailTasteRanges(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            wineRecord = WineRecord(
                body = 5f,
                tannin = 4f,
                sugarContent = 3f,
                acidity = 2f
            )
        )
    }
}