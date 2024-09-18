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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.StepSize
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.Gray20
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.designsystem.theme.Yellow
import com.winenote.core.model.WineRecord
import com.winenote.core.resource.R
import com.winenote.core.ui.util.getDateTimeFormatString

@Composable
fun WineDetailTotalComment(
    modifier: Modifier = Modifier,
    wineRecord: WineRecord,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.detail_total),
            style = WineTheme.typography.bold14,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = wineRecord.comment,
            style = WineTheme.typography.regular14,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            RatingBar(
                value = wineRecord.score,
                config = RatingBarConfig()
                    .size(24.dp)
                    .padding(2.dp)
                    .activeColor(Yellow)
                    .inactiveColor(Gray20)
                    .stepSize(StepSize.HALF)
                    .hideInactiveStars(false),
                onValueChange = {},
                onRatingChanged = {},
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = getDateTimeFormatString(wineRecord.updatedAt,  stringResource(id = R.string.regex_date)),
                style = WineTheme.typography.regular12,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@ThemePreviews
@Composable
private fun WineDetailTotalCommentPreview() {
    WineNoteTheme {
        WineDetailTotalComment(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            wineRecord = WineRecord(
                comment = "마치 이베리아 반도의 탱고의 여인, 탱고를 추는 여인. 하지만 그 여인이 친숙하게 느껴지는 그런 느낌을 받았다.",
                score = 3.0f
            )
        )
    }
}