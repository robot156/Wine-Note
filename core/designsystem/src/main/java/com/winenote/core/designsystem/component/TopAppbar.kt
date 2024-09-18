package com.winenote.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.Gray80
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R

@Composable
fun WineTopAppbar(
    modifier: Modifier = Modifier,
    thickness: Dp = 0.5.dp,
    dividerColor: Color = Gray80,
    title: String = "",
    titleStyle: TextStyle = WineTheme.typography.regular16,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    onClickNavigate: () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_prev),
            contentDescription = "back"
        )
    },
    actions: @Composable RowScope.() -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        CenterAlignedTopAppBar(
            modifier = modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .statusBarsPadding(),
            title = {
                Text(
                    text = title,
                    style = titleStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        onClickNavigate()
                    }
                ) {
                    navigationIcon()
                }
            },
            actions = actions,
            windowInsets = WindowInsets(top = 0.dp),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = MaterialTheme.colorScheme.onSurface
            )
        )

        if (thickness != 0.dp) {
            HorizontalDivider(thickness = thickness, color = dividerColor)
        }
    }
}


@ThemePreviews
@Composable
private fun WineTopAppbarPreview() {
    WineNoteTheme {
        WineTopAppbar(
            title = "TEXT",
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = ""
                )
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = ""
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = ""
                    )
                }
            },
            onClickNavigate = {},
        )
    }
}