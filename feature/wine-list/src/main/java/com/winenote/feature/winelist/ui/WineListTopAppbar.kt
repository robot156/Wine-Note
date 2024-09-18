package com.winenote.feature.winelist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R
import com.winenote.feature.winelist.OnWineListUiAction
import com.winenote.feature.winelist.WineListUiAction

@Composable
fun WineListTopAppbar(
    modifier: Modifier = Modifier,
    onUiAction: OnWineListUiAction = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 14.dp, start = 18.dp, end = 18.dp, bottom = 20.dp)
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.main_title),
                style = WineTheme.typography.bold24,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(id = R.string.main_sub_title),
                style = WineTheme.typography.bold18,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { onUiAction(WineListUiAction.OnClickSetting) }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_setting),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@ThemePreviews
@Composable
private fun WineListTopAppbarPreview() {
    WineNoteTheme {
        WineListTopAppbar()
    }
}