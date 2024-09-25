package com.winenote.feature.winewrite

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.component.WineScaffold
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.route.Route
import com.winenote.core.ui.common.EmptyScreen
import com.winenote.core.ui.common.LoadingDialog
import com.winenote.core.ui.util.ObserveAsEvents
import com.winenote.core.ui.util.getDateFormatLongTime
import com.winenote.feature.winewrite.state.WriteScreenState
import com.winenote.feature.winewrite.ui.DatePickerDialog
import com.winenote.feature.winewrite.ui.WineWriteBottomBar
import com.winenote.feature.winewrite.ui.WineWriteTopAppbar
import com.winenote.feature.winewrite.ui.step.WineColorScentScreen
import com.winenote.feature.winewrite.ui.step.WineInfoScreen
import com.winenote.feature.winewrite.ui.step.WineTasteScreen
import com.winenote.feature.winewrite.ui.step.WineTotalScreen
import com.winenote.feature.winewrite.util.getTransitionDirection

internal typealias OnWineWriteUiAction = (WineWriteUiAction) -> Unit

@Composable
internal fun WineWriteRoute(
    viewModel: WineWriteViewModel = hiltViewModel(),
    navigateToWineDetail: (String, NavOptions) -> Unit,
    navigateToBack: () -> Unit,
) {
    val context = LocalContext.current
    val navOptions = NavOptions.Builder()
        .setPopUpTo(Route.WineList, inclusive = false)
        .build()

    val wineWriteUiState by viewModel.wineWriteUiState.collectAsStateWithLifecycle()
    val isLoading by viewModel.loading.collectAsStateWithLifecycle()
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> if (uri != null) viewModel.onUiAction(WineWriteUiAction.OnChangeWinePhoto(uri.toString())) }
    )

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is WineWriteUiEvent.NavigateToBack -> navigateToBack()
            is WineWriteUiEvent.NavigateToWineDetail -> navigateToWineDetail(event.recordId, navOptions)
            is WineWriteUiEvent.NavigateToPhotoPicker -> singlePhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            is WineWriteUiEvent.ShowToastMessage -> Toast.makeText(context, event.messageRes, Toast.LENGTH_SHORT).show()
        }
    }

    BackHandler {
        viewModel.onUiAction(WineWriteUiAction.OnClickBack)
    }

    when (val uiState = wineWriteUiState) {
        is WineWriteUiState.None -> EmptyScreen()
        is WineWriteUiState.WineWrite -> {
            WineWriteScreen(
                uiState = uiState,
                onUiAction = remember { viewModel::onUiAction }
            )
        }
    }

    LoadingDialog(isLoading = isLoading)
}

@Composable
fun WineWriteScreen(
    modifier: Modifier = Modifier,
    uiState: WineWriteUiState.WineWrite,
    onUiAction: OnWineWriteUiAction
) {
    WineScaffold(
        modifier = modifier,
        topBar = {
            WineWriteTopAppbar(
                uiState = uiState,
                onUiAction = onUiAction
            )
        },
        content = { paddingValues ->
            WineWriteScreenAnimation(uiState.currentState) { targetState ->
                val contentModifier = Modifier.padding(paddingValues)

                when (targetState) {
                    WriteScreenState.Information -> WineInfoScreen(
                        modifier = contentModifier,
                        uiState = uiState,
                        onUiAction = onUiAction
                    )

                    WriteScreenState.ColorScant -> WineColorScentScreen(
                        modifier = contentModifier,
                        uiState = uiState,
                        onUiAction = onUiAction
                    )

                    WriteScreenState.Taste -> WineTasteScreen(
                        modifier = contentModifier,
                        uiState = uiState,
                        onUiAction = onUiAction
                    )

                    WriteScreenState.Total -> WineTotalScreen(
                        modifier = contentModifier,
                        uiState = uiState,
                        onUiAction = onUiAction
                    )
                }
            }
        },
        bottomBar = {
            WineWriteBottomBar(
                uiState = uiState,
                onUiAction = onUiAction
            )
        }
    )

    if (uiState.isShowDatePickerDialog) {
        DatePickerDialog(
            date = getDateFormatLongTime(uiState.record.updatedAt),
            onDateSelected = { onUiAction(WineWriteUiAction.OnChangeRecordDate(it)) },
            onDismiss = { onUiAction(WineWriteUiAction.OnShowDatePickerDialog(false)) }
        )
    }
}

@Composable
private fun WineWriteScreenAnimation(
    screenState: WriteScreenState,
    content: @Composable AnimatedContentScope.(targetState: WriteScreenState) -> Unit
) {
    AnimatedContent(
        targetState = screenState,
        transitionSpec = {
            val animationSpec: TweenSpec<IntOffset> = tween(500)
            val direction = getTransitionDirection(
                initialIndex = initialState.index,
                targetIndex = targetState.index
            )

            slideIntoContainer(
                towards = direction,
                animationSpec = animationSpec,
            ) togetherWith slideOutOfContainer(
                towards = direction,
                animationSpec = animationSpec
            )
        },
        label = "WineWriteScreenAnimation",
        content = content
    )
}

@ThemePreviews
@Composable
private fun WineWriteScreenPreview() {
    WineNoteTheme {
        WineWriteScreen(
            uiState = WineWriteUiState.WineWrite(),
            onUiAction = {}
        )
    }
}