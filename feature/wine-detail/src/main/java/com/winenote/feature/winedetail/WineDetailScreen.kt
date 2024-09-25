package com.winenote.feature.winedetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.component.WineScaffold
import com.winenote.core.designsystem.modifier.singleClickable
import com.winenote.core.designsystem.theme.Gray
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.model.WineRecord
import com.winenote.core.resource.R
import com.winenote.core.ui.common.EmptyScreen
import com.winenote.core.ui.util.ObserveAsEvents
import com.winenote.core.ui.util.externalShareForBitmap
import com.winenote.feature.winedetail.ui.WineDetailInformation
import com.winenote.feature.winedetail.ui.WineDetailPhotoDialog
import com.winenote.feature.winedetail.ui.WineDetailTasteRanges
import com.winenote.feature.winedetail.ui.WineDetailTopAppbar
import com.winenote.feature.winedetail.ui.WineDetailTotalComment
import com.winenote.feature.winedetail.ui.edit.WineDetailEditDialog

internal typealias OnWineDetailUiAction = (WineDetailUiAction) -> Unit

@Composable
internal fun WineDetailRoute(
    viewModel: WineDetailViewModel = hiltViewModel(),
    navigateToWineWrite: (String) -> Unit,
    navigateToBack: () -> Unit,
) {
    val context = LocalContext.current
    val wineDetailUiState by viewModel.wineDetailUiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is WineDetailEvent.NavigateToBack -> navigateToBack()
            is WineDetailEvent.NavigateToWineWrite -> navigateToWineWrite(event.recordId)
            is WineDetailEvent.NavigateToShareRecord -> context.externalShareForBitmap(event.bitmap)
        }
    }

    when (val uiState = wineDetailUiState) {
        is WineDetailUiState.None -> EmptyScreen()
        is WineDetailUiState.WineDetail -> WineDetailScreen(
            uiState = uiState,
            onUiAction = remember { viewModel::onUiAction }
        )
    }
}

@Composable
fun WineDetailScreen(
    modifier: Modifier = Modifier,
    uiState: WineDetailUiState.WineDetail,
    onUiAction: OnWineDetailUiAction,
) {
    WineScaffold(
        modifier = modifier,
        topBar = { WineDetailTopAppbar(onUiAction = onUiAction) },
        content = {
            WineDetailContainer(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                uiState = uiState,
                onUiAction = onUiAction
            )
        }
    )

    if (uiState.isShowEditDialog) {
        WineDetailEditDialog(
            isDeleted = uiState.record.isDelete,
            onUiAction = onUiAction
        )
    }
    if (uiState.isShowPhotoDialog) {
        WineDetailPhotoDialog(
            photoUrl = uiState.record.photoUrl!!,
            onUiAction = onUiAction
        )
    }
}

@Composable
fun WineDetailContainer(
    modifier: Modifier = Modifier,
    uiState: WineDetailUiState.WineDetail,
    onUiAction: OnWineDetailUiAction = {}
) {
    val graphicsLayer = rememberGraphicsLayer()

    LaunchedEffect(uiState.isShared) {
        if (uiState.isShared) onUiAction(WineDetailUiAction.OnShareRecord(graphicsLayer.toImageBitmap()))
    }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .drawWithContent {
                    graphicsLayer.record { this@drawWithContent.drawContent() }
                    drawLayer(graphicsLayer)
                },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                WineDetailInformation(wineRecord = uiState.record)
                WineDetailTasteRanges(wineRecord = uiState.record)
                WineDetailTotalComment(wineRecord = uiState.record)

                if (uiState.record.photoUrl != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.background),
                        shape = MaterialTheme.shapes.small,
                        colors = CardDefaults.cardColors(containerColor = Gray)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .singleClickable { onUiAction(WineDetailUiAction.OnShowPhotoDialog(true)) }
                                .aspectRatio(1f),
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(uiState.record.photoUrl)
                                .build(),
                            placeholder = painterResource(R.drawable.bg_placeholder),
                            contentDescription = "record image",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun WineDetailScreenPreview() {
    WineNoteTheme {
        WineDetailScreen(
            uiState = WineDetailUiState.WineDetail(
                record = WineRecord(
                    name = "8월의 와인",
                    region = "캘리포니아",
                    grapeVariety = "소비뇽",
                    scentFeel = "열대 과일 향이 난다.",
                    comment = "혼술하기 좋음"
                )
            ),
            onUiAction = {}
        )
    }
}
