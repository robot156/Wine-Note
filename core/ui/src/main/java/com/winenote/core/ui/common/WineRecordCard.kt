package com.winenote.core.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.modifier.singleClickable
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
fun WineRecordCard(
    modifier: Modifier = Modifier,
    record: WineRecord = WineRecord(),
    onClickWineRecord: (String) -> Unit = {},
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4))
            .singleClickable { onClickWineRecord(record.id) }
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(width = 14.dp, height = 44.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_wine_bottle),
            contentDescription = "",
            tint = when (record.color) {
                WineColor.Red -> RedWine
                WineColor.Rose -> RoseWine
                WineColor.White -> WhiteWine
                WineColor.Dessert -> DessertWine
                WineColor.Sparkling -> SparklingWine
            }
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 14.dp),
            text = record.name,
            style = WineTheme.typography.regular16,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Icon(
            modifier = Modifier.padding(4.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
            contentDescription = "",
            tint = Color.Unspecified
        )

        Text(
            text = record.score.toString(),
            style = WineTheme.typography.bold12,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@ThemePreviews
@Composable
private fun WineRecordCardPreview() {
    WineNoteTheme {
        WineRecordCard(
            record = WineRecord(
                name = "오늘의 와인"
            )
        )
    }
}