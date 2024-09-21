package com.winenote.feature.setting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.component.WineDialog
import com.winenote.core.designsystem.component.WinePrimaryButton
import com.winenote.core.designsystem.theme.Gray80
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.model.ThemeConfig
import com.winenote.core.resource.R
import com.winenote.feature.setting.OnSettingUiAction
import com.winenote.feature.setting.SettingUiAction

@Composable
fun SettingsThemeChooserDialog(
    currentTheme: ThemeConfig,
    onUiAction: OnSettingUiAction
) {
    val configuration = LocalConfiguration.current
    val dismissAction = SettingUiAction.OnShowThemeDialog(false)
    var selectedTheme by remember { mutableStateOf(currentTheme) }

    WineDialog(
        onDismiss = { onUiAction(dismissAction) }
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                .widthIn(max = configuration.screenWidthDp.dp - 80.dp)
                .padding(12.dp),
        ) {
            Text(
                modifier = Modifier.padding(6.dp),
                text = stringResource(id = R.string.setting_theme_change),
                style = WineTheme.typography.bold18,
                color = MaterialTheme.colorScheme.onSurface
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 6.dp),
                color = Gray80,
                thickness = 0.5.dp
            )

            SettingsThemeChooserRow(
                text = stringResource(id = R.string.setting_theme_system),
                selected = selectedTheme == ThemeConfig.FOLLOW_SYSTEM,
                onClick = { selectedTheme = ThemeConfig.FOLLOW_SYSTEM }
            )
            SettingsThemeChooserRow(
                text = stringResource(id = R.string.setting_theme_light),
                selected = selectedTheme == ThemeConfig.LIGHT,
                onClick = { selectedTheme = ThemeConfig.LIGHT }
            )
            SettingsThemeChooserRow(
                text = stringResource(id = R.string.setting_theme_dark),
                selected = selectedTheme == ThemeConfig.DARK,
                onClick = { selectedTheme = ThemeConfig.DARK }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(
                    onClick = { onUiAction(dismissAction) }
                ) {
                    Text(
                        text = stringResource(id = R.string.common_cancel),
                        style = WineTheme.typography.regular16,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                
                Spacer(modifier = Modifier.width(8.dp))

                WinePrimaryButton(
                    text = stringResource(id = R.string.common_confirm),
                    onClick = { onUiAction(SettingUiAction.OnClickThemeSetting(selectedTheme)) }
                )
            }
        }
    }
}

@Composable
fun SettingsThemeChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(vertical = 12.dp, horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = text,
            style = WineTheme.typography.regular16,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
