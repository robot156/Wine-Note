package com.winenote.feature.winewrite.ui.step

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.StepSize
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.modifier.singleClickable
import com.winenote.core.designsystem.theme.Gray
import com.winenote.core.designsystem.theme.Gray20
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.designsystem.theme.Yellow
import com.winenote.core.model.WineRecord
import com.winenote.core.resource.R
import com.winenote.feature.winewrite.OnWineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiAction
import com.winenote.feature.winewrite.WineWriteUiState
import com.winenote.feature.winewrite.ui.WineWriteTextField

@Composable
fun WineTotalScreen(
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
    ) {
        Text(
            text = stringResource(id = R.string.write_total_title),
            style = WineTheme.typography.bold18,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(30.dp))

        WineScoreBar(
            uiState = uiState,
            onUiAction = onUiAction
        )
        Spacer(modifier = Modifier.height(20.dp))

        WinePhoto(
            photoUrl = uiState.record.photoUrl,
            onUiAction = onUiAction
        )
        Spacer(modifier = Modifier.height(20.dp))

        WineWriteTextField(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.write_total_comment),
            value = uiState.record.comment,
            hintText = stringResource(id = R.string.write_total_comment_hint),
            maxLine = 10,
            imeAction = ImeAction.None,
            onValueChange = { onUiAction(WineWriteUiAction.OnChangeWineComment(it)) }
        )
    }
}

@Composable
private fun WineScoreBar(
    uiState: WineWriteUiState.WineWrite,
    onUiAction: OnWineWriteUiAction
) {
    var score by remember { mutableFloatStateOf(uiState.record.score) }
    val comment = stringArrayResource(id = R.array.score_comment).let { comments ->
        when (score) {
            0.0f -> comments[0]
            0.5f -> comments[1]
            1.0f -> comments[2]
            1.5f -> comments[3]
            2.0f -> comments[4]
            2.5f -> comments[5]
            3.0f -> comments[6]
            3.5f -> comments[7]
            4.0f -> comments[8]
            4.5f -> comments[9]
            else -> comments[10]
        }
    }

    Text(
        text = stringResource(id = R.string.write_score),
        style = WineTheme.typography.bold14,
        color = MaterialTheme.colorScheme.onSurface,
    )
    Spacer(modifier = Modifier.height(10.dp))
    RatingBar(
        value = score,
        config = RatingBarConfig()
            .size(42.dp)
            .padding(2.dp)
            .activeColor(Yellow)
            .inactiveColor(Gray20)
            .stepSize(StepSize.HALF)
            .hideInactiveStars(false),
        onValueChange = { score = it },
        onRatingChanged = { onUiAction(WineWriteUiAction.OnChangeWineScore(it)) }
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = comment,
        style = WineTheme.typography.regular12,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@Composable
private fun WinePhoto(
    photoUrl: String?,
    onUiAction: OnWineWriteUiAction
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = R.string.write_photo),
            style = WineTheme.typography.bold14,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(id = R.string.write_photo_option),
            style = WineTheme.typography.regular12,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    if (photoUrl == null) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(6.dp)
                )
                .size(60.dp)
                .singleClickable { onUiAction(WineWriteUiAction.OnNavigatePhotoPicker(true)) }
                .padding(16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_photo),
                contentDescription = "photo",
                tint = Color.White
            )
        }
    } else {
        Box(
            modifier = Modifier.singleClickable { onUiAction(WineWriteUiAction.OnChangeWinePhoto(null)) }
        ) {
            Card(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.background),
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(containerColor = Gray)
            ) {
                AsyncImage(
                    modifier = Modifier.size(60.dp),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(photoUrl)
                        .build(),
                    contentDescription = "record image",
                    contentScale = ContentScale.Crop
                )
            }
        }

    }
}

@Composable
@ThemePreviews
private fun WineTotalScreenPreview() {
    WineNoteTheme {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            WineTotalScreen(
                uiState = WineWriteUiState.WineWrite(record = WineRecord()),
                onUiAction = {}
            )
        }
    }
}