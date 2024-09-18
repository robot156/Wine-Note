package com.winenote.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.winenote.core.designsystem.ThemePreviews
import com.winenote.core.designsystem.theme.Red100
import com.winenote.core.designsystem.theme.Red80
import com.winenote.core.designsystem.theme.WineNoteTheme
import com.winenote.core.designsystem.theme.WineTheme
import com.winenote.core.designsystem.theme.Yellow
import com.winenote.core.resource.R

@Composable
internal fun SplashRoute(
    splashViewModel: SplashViewModel = hiltViewModel(),
    navigateToWineList: (NavOptions) -> Unit
) {
    val uiState by splashViewModel.uiState.collectAsStateWithLifecycle()
    val options = NavOptions.Builder()
        .setPopUpTo(splashRoute, inclusive = true)
        .build()

    when(uiState) {
        is SplashUiState.Loading -> Unit
        is SplashUiState.Ready -> navigateToWineList(options)
    }

    SplashScreen()
}

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            SplashLogo()

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                style = WineTheme.typography.bold24,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun ColumnScope.SplashLogo() {
    Row(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        WineBottle(
            modifier = Modifier.size(width = 36.dp, height = 120.dp),
            color = Red100
        )

        WineBottle(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(width = 24.dp, height = 92.dp)
                .align(Alignment.Bottom),
            color = Yellow
        )

        WineBottle(
            modifier = Modifier.size(width = 36.dp, height = 120.dp),
            color = Red80
        )
    }
}

@Composable
private fun WineBottle(
    modifier: Modifier = Modifier,
    color: Color
) {
    Icon(
        modifier = modifier,
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_wine_bottle),
        contentDescription = "",
        tint = color
    )
}

@ThemePreviews
@Composable
private fun SplashPreview() {
    WineNoteTheme {
        SplashScreen()
    }
}