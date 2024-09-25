package com.winenote.feature.winebin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.winenote.core.route.Route

fun NavGraphBuilder.wineBinNavigation(
    navigateToBack: () -> Unit,
    navigateToWineDetail: (String) -> Unit
) {
    composable<Route.WineBin> {
        WineBinRoute(
            navigateToBack = navigateToBack,
            navigateToWineDetail = navigateToWineDetail
        )
    }
}

fun NavController.navigateToWineBin(navOptions: NavOptions? = null) {
    this.navigate(Route.WineBin, navOptions)
}