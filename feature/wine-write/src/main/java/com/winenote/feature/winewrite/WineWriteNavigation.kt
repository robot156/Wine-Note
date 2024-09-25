package com.winenote.feature.winewrite

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.winenote.core.route.Route

internal class WriteArgs(val recordId: String? = null) {
    constructor(savedStateHandle: SavedStateHandle) : this(savedStateHandle["recordId"])
}

fun NavGraphBuilder.wineWriteNavigation(
    navigateToWineDetail: (String, NavOptions) -> Unit,
    navigateToBack: () -> Unit
) {
    composable<Route.WineWrite> {
        WineWriteRoute(
            navigateToWineDetail = navigateToWineDetail,
            navigateToBack = navigateToBack
        )
    }
}

fun NavController.navigateToWineWrite(recordId: String? = null, navOptions: NavOptions? = null) {
    navigate(Route.WineWrite(recordId), navOptions)
}