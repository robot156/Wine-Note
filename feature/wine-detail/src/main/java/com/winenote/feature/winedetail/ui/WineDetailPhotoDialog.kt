package com.winenote.feature.winedetail.ui

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.winenote.core.designsystem.component.WineDialog
import com.winenote.core.designsystem.component.WineScaffold
import com.winenote.core.designsystem.component.WineTopAppbar
import com.winenote.core.designsystem.theme.Black90
import com.winenote.core.designsystem.theme.White90
import com.winenote.core.resource.R
import com.winenote.feature.winedetail.OnWineDetailUiAction
import com.winenote.feature.winedetail.WineDetailUiAction

@Composable
fun WineDetailPhotoDialog(
    photoUrl: String = "",
    onUiAction: OnWineDetailUiAction = {}
) {
    WineDialog(
        usePlatformDefaultWidth = false,
        onDismiss = { onUiAction(WineDetailUiAction.OnShowPhotoDialog(false)) }
    ) {
        WineDetailPhotoScreen(
            photoUrl = photoUrl,
            onUiAction = onUiAction
        )
    }
}

@Composable
private fun WineDetailPhotoScreen(
    photoUrl: String,
    onUiAction: OnWineDetailUiAction = {},
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var rotation by remember { mutableFloatStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }

    WineScaffold(
        backgroundColor = Black90,
        topBar = {
            WineTopAppbar(
                backgroundColor = Black90,
                navigationIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                        contentDescription = "back",
                        tint = White90
                    )
                },
                thickness = 0.dp,
                onClickNavigate = { onUiAction(WineDetailUiAction.OnShowPhotoDialog(false)) }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(12.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    .transformable(state = state)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(photoUrl)
                        .build(),
                    placeholder = painterResource(R.drawable.bg_placeholder),
                    contentDescription = "record image",
                    contentScale = ContentScale.None
                )
            }
        }
    )
}