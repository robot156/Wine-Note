package com.winenote.feature.winelist.ui.type

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.resource.R

@Composable
fun WineListEmptyScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.rotate(25f),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_wine_bottle),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(id = R.string.main_empty_wine),
            style = WineTheme.typography.regular16,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@ThemePreviews
@Composable
private fun WineListEmptyScreenPreview() {
    WineNoteTheme {
        WineListEmptyScreen()
    }
}