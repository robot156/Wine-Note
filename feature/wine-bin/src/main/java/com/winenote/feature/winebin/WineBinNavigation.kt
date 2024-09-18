package com.winenote.feature.winebin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val wineBinRoute = "wine_bin_route"

fun NavGraphBuilder.wineBinNavigation(
    navigateToBack: () -> Unit,
    navigateToWineDetail: (String) -> Unit
) {
    composable(wineBinRoute) {
        WineBinRoute(
            navigateToBack = navigateToBack,
            navigateToWineDetail = navigateToWineDetail
        )
    }
}

fun NavController.navigateToWineBin(navOptions: NavOptions? = null) {
    this.navigate(wineBinRoute, navOptions)
}