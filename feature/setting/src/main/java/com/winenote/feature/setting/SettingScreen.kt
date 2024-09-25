package com.winenote.feature.setting

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.component.WineAlertDialog
import com.winenote.core.designsystem.component.WineScaffold
import com.winenote.core.designsystem.component.WineTopAppbar
import com.winenote.core.designsystem.modifier.singleClickable
import com.winenote.core.designsystem.theme.Gray80
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R
import com.winenote.core.ui.util.ObserveAsEvents
import com.winenote.feature.setting.ui.SettingsThemeChooserDialog

internal typealias OnSettingUiAction = (SettingUiAction) -> Unit

@Composable
internal fun SettingRoute(
    viewModel: SettingViewModel = hiltViewModel(),
    navigateToBinRecord: () -> Unit,
    navigateToBack: () -> Unit
) {
    val context = LocalContext.current
    val settingUiState by viewModel.settingUiState.collectAsStateWithLifecycle()

    BackHandler {
        viewModel.onUiAction(SettingUiAction.OnClickBack)
    }

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is SettingUiEvent.NavigateToBack -> navigateToBack()
            is SettingUiEvent.NavigateToBin -> navigateToBinRecord()
            is SettingUiEvent.ShowToastMessage -> Toast.makeText(context, event.messageRes, Toast.LENGTH_SHORT).show()
        }
    }

    when (val uiState = settingUiState) {
        is SettingUiState.Setting -> {
            SettingScreen(
                uiState = uiState,
                onUiAction = remember { viewModel::onUiAction }
            )
        }
    }
}

@Composable
fun SettingScreen(
    uiState: SettingUiState.Setting,
    onUiAction: OnSettingUiAction
) {
    WineScaffold(
        topBar = {
            WineTopAppbar(
                title = stringResource(id = R.string.setting_title),
                onClickNavigate = { onUiAction(SettingUiAction.OnClickBack) }
            )
        },
        content = {
            SettingContent(
                modifier = Modifier.padding(it),
                onUiAction = onUiAction
            )
        }
    )

    if (uiState.isShowRecordClearDialog) {
        val dismissAction = SettingUiAction.OnShowRecordClearDialog(false)

        WineAlertDialog(
            title = stringResource(id = R.string.setting_clear),
            description = stringResource(id = R.string.setting_clear_description),
            onClickPositive = { onUiAction(SettingUiAction.OnClickRecordClear) },
            onClickNegative = { onUiAction(dismissAction) },
            onDismiss = { onUiAction(dismissAction) }
        )
    }

    if (uiState.isShowThemeDialog) {
        SettingsThemeChooserDialog(
            currentTheme = uiState.currentThemeConfig,
            onUiAction = onUiAction
        )
    }
}

@Composable
fun SettingContent(
    modifier: Modifier = Modifier,
    onUiAction: OnSettingUiAction,
) {
    Column(
        modifier = modifier,
    ) {
        SettingRow(
            onClick = { onUiAction(SettingUiAction.OnClickBin) }
        ) {
            Text(
                text = stringResource(id = R.string.setting_bin),
                style = WineTheme.typography.regular16,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_next),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        HorizontalDivider(color = Gray80, thickness = 0.5.dp)

        SettingRow(
            onClick = { onUiAction(SettingUiAction.OnShowThemeDialog(true)) }
        ) {
            Text(
                text = stringResource(id = R.string.setting_theme_change),
                style = WineTheme.typography.regular16,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        HorizontalDivider(color = Gray80, thickness = 0.5.dp)

        SettingRow(
            onClick = { onUiAction(SettingUiAction.OnShowRecordClearDialog(true)) }
        ) {
            Text(
                text = stringResource(id = R.string.setting_clear),
                style = WineTheme.typography.regular16,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        HorizontalDivider(color = Gray80, thickness = 0.5.dp)

        SettingRow(
            isClickable = false
        ) {
            Text(
                text = stringResource(id = R.string.setting_version),
                style = WineTheme.typography.regular16,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.setting_version_value, BuildConfig.VERSION_NAME),
                style = WineTheme.typography.regular16,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun SettingRow(
    onClick: () -> Unit = {},
    isClickable: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .singleClickable(
                enabled = isClickable,
                onClick = onClick
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@ThemePreviews
@Composable
private fun SettingScreenPreview() {
    WineNoteTheme {
        SettingScreen(
            uiState = SettingUiState.Setting(),
            onUiAction = {}
        )
    }
}