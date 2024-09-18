package com.winenote.feature.winedetail.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.winenote.core.designsystem.component.WineTopAppbar
import com.winenote.core.resource.R
import com.winenote.feature.winedetail.OnWineDetailUiAction
import com.winenote.feature.winedetail.WineDetailUiAction

@Composable
fun WineDetailTopAppbar(
    modifier: Modifier = Modifier,
    onUiAction: OnWineDetailUiAction = {}
) {
    WineTopAppbar(
        modifier = modifier,
        title = stringResource(id = R.string.detail_title),
        actions = {
            IconButton(
                onClick = { onUiAction(WineDetailUiAction.OnClickShareRecord(true)) }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { onUiAction(WineDetailUiAction.OnShowEditDialog(true)) }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        onClickNavigate = { onUiAction(WineDetailUiAction.OnClickBack) },
    )
}