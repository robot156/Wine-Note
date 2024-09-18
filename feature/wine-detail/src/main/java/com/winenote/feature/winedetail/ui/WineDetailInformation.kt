package com.winenote.feature.winedetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringArrayResource
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

@Composable
fun WineDetailInformation(
    modifier: Modifier = Modifier,
    wineRecord: WineRecord,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = wineRecord.name,
            style = WineTheme.typography.bold18,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(width = 14.dp, height = 56.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_wine_bottle),
                contentDescription = "",
                tint = when (wineRecord.color) {
                    WineColor.Red -> RedWine
                    WineColor.Rose -> RoseWine
                    WineColor.White -> WhiteWine
                    WineColor.Dessert -> DessertWine
                    WineColor.Sparkling -> SparklingWine
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                val wineInfoSummary = getWineInformation(wineRecord)

                if (wineInfoSummary.isNotEmpty()) {
                    Text(
                        text = wineInfoSummary,
                        style = WineTheme.typography.regular12,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = stringResource(id = R.string.detail_scent, getScantComment(scantValue = wineRecord.scent.toInt())),
                    style = WineTheme.typography.regular12,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (wineRecord.scentFeel.isNotEmpty()) {
            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(id = R.string.detail_scent_feel),
                style = WineTheme.typography.bold14,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = wineRecord.scentFeel,
                style = WineTheme.typography.regular12,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun getWineInformation(wineRecord: WineRecord): String {
    val information = mutableListOf(wineRecord.region, wineRecord.grapeVariety)
        .apply {
            if (wineRecord.alcohol > 0.0f) {
                add(wineRecord.alcohol.toString().plus("%"))
            }
        }
        .filter { it.isNotEmpty() }

    return information.joinToString(" · ")
}

@Composable
private fun getScantComment(scantValue: Int): String {
    return stringArrayResource(id = R.array.scant_comment)[scantValue - 1]
}

@ThemePreviews
@Composable
private fun WineDetailInformationPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp)
        ) {

            WineDetailInformation(
                wineRecord = WineRecord(
                    name = "8월의 와인",
                    scentFeel = "포도 향, 열대 과일 향, 훈연 향, 그 외 기타 등등의 향을 느꼈다. 포도 향, 열대 과일 향, 훈연 향, 그 외 기타 등등의 향을 느꼈다.",
                    region = "USA",
                    grapeVariety = "소비뇽",
                    alcohol = 2f
                )
            )
        }
    }
}