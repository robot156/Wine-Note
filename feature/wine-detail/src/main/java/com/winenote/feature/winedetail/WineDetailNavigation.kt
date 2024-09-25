package com.winenote.feature.winedetail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.winenote.core.route.Route

internal class DetailArgs(val recordId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(checkNotNull(savedStateHandle["recordId"]).toString())
}

fun NavGraphBuilder.wineDetailNavigation(
    navigateToWineWrite: (String) -> Unit,
    navigateToBack: () -> Unit
) {
    composable<Route.WineDetail>{
        WineDetailRoute(
            navigateToWineWrite = navigateToWineWrite,
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToWineDetail(recordId: String, navOptions: NavOptions? = null) {
    navigate(Route.WineDetail(recordId), navOptions)
}